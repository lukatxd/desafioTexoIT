package com.texoit.worstmovie.model;

import java.util.Objects;

public class AwardInterval {
	
	public AwardInterval() {}

	public AwardInterval(Producer producer, int interval, int previousWin, int followingWin) {
		this.producer = producer.getName();
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;


	@Override
	public int hashCode() {
		return Objects.hash(followingWin, interval, previousWin, producer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AwardInterval other = (AwardInterval) obj;
		return followingWin == other.followingWin && interval == other.interval && previousWin == other.previousWin
				&& Objects.equals(producer, other.producer);
	}

	@Override
	public String toString() {
		return "AwardInterval [producer=" + producer + ", interval=" + interval + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + "]";
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}

}
