package com.texoit.worstmovie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.texoit.worstmovie.model.Movie;
import com.texoit.worstmovie.services.MovieService;
import com.texoit.worstmovie.services.movieBuilder.MovieBuilder;
import com.texoit.worstmovie.services.movieBuilder.MovieBuilderObject;

@Configuration
public class DatabaseFiller {

	@Autowired
	private MovieService movieService;
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private MovieBuilder movieBuilder;

	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		List<Movie> movies = getMoviesFromResourcesFiles();
		movieService.saveAll(movies);
	}

	private List<Movie> getMoviesFromResourcesFiles() {
		String filePath = (String)appContext.getEnvironment().getProperty(ConfigurationProperties.DATABASEFILLER_FOLDER);
		File rootFolder = new File(filePath);
		List<Movie> movies = readFiles(rootFolder);
		return movies;
	}

	private List<Movie> readFiles(File rootFolder) {
		List<Movie> movies = new ArrayList<>();
		if(null == rootFolder)
			return movies;
		
		FilenameFilter filenameFilter = new WildcardFileFilter("*.csv");
		for (File file : rootFolder.listFiles(filenameFilter)) {
			if (file.isDirectory()) {
				movies.addAll(readFiles(file));
			} else {
				try {
					movies.addAll(getMovies(file));
				} catch (Exception e) {
					continue;
				}
			}
		}
		return movies;
	}

	private List<Movie> getMovies(File file) {
		List<Movie> movies = new ArrayList<>();
		try (InputStream in = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			// to skip headers
			br.readLine();

			String line;
			while ((line = br.readLine()) != null) {
				String[] movieData = line.split(";");
				try {
					
					MovieBuilderObject mb = movieBuilder.startBuilder(); 
					mb.onYear(Integer.parseInt(movieData[0]))
					.withTitle(movieData[1])
					.addStudios(movieData[2])
					.addProducers(movieData[3]);
					
					if(movieData.length >4) {
						mb.wonAward();
					}
					
					movies.add(mb.build());
				} catch (NumberFormatException nfeException) {
					continue;
				} catch (IndexOutOfBoundsException iobException) {
					continue;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}
}
