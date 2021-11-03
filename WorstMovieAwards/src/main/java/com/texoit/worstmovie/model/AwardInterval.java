package com.texoit.worstmovie.model;

import java.util.Objects;

public class AwardInterval {

	public AwardInterval(Producer producer, int interval, int previousWin, int followingWin) {
		this.producerName = producer.getName();
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	private String producerName;
	private int interval;
	private int previousWin;
	private int followingWin;

	public String getProducer() {
		return producerName;
	}

	public int getInterval() {
		return interval;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followingWin, interval, previousWin, producerName);
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
				&& Objects.equals(producerName, other.producerName);
	}

	@Override
	public String toString() {
		return "AwardInterval [producer=" + producerName + ", interval=" + interval + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + "]";
	}

}
