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
import org.springframework.core.io.ClassPathResource;

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
		File rootFile;
		try {
			rootFile = pickFileToRead();
		} catch (IOException ioex) {
			throw new RuntimeException();
		}
		List<Movie> movies = readFiles(rootFile);
		return movies;
	}

	private File pickFileToRead() throws IOException {
		final String filePath = (String) appContext.getEnvironment()
				.getProperty(ConfigurationProperties.DATABASEFILLER_FOLDER);

		return filePath.isEmpty() ? new ClassPathResource("movies/movielist.csv").getFile() : new File(filePath);
	}

	private List<Movie> readFiles(File rootFile) {
		List<Movie> movies = new ArrayList<>();
		if (null == rootFile)
			return movies;

		if (rootFile.isDirectory()) {
			FilenameFilter filenameFilter = new WildcardFileFilter("*.csv");
			for (File file : rootFile.listFiles(filenameFilter)) {
				movies.addAll(readFiles(file));
			}
		} else {
			movies.addAll(getMovies(rootFile));
		}
		return movies;
	}

	private List<Movie> getMovies(File file) {
		List<Movie> movies = new ArrayList<>();
		try (InputStream in = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

			String line = br.readLine();
			if (!validateHeaders(line))
				return new ArrayList<>();

			while ((line = br.readLine()) != null) {
				String[] movieData = line.split(";");
				try {

					MovieBuilderObject mb = movieBuilder.startBuilder();
					mb.onYear(Integer.parseInt(movieData[0])).withTitle(movieData[1]).withStudios(movieData[2])
							.withProducers(movieData[3]);

					if (movieData.length > 4) {
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

	private boolean validateHeaders(String firstLine) {
		if(null != firstLine && firstLine.equals("year;title;studios;producers;winner"))
			return true;
		return false;
	}
}
