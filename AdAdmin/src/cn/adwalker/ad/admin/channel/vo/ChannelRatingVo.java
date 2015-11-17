package cn.adwalker.ad.admin.channel.vo;

public class ChannelRatingVo {
	private Long channelId;
	private String channelName;
	private String os;
	private Double scale;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getlevel() {
		String str = null;
		Integer i = getScale_int();
		if (i != null) {
			if (i > 130 && i <= 150) {
				str = "A";
			} else if (i > 100 && i <= 130) {
				str = "B";
			} else if (i > 70 && i <= 100) {
				str = "C";
			} else if (i > 40 && i <= 70) {
				str = "D";
			} else if (i >= 0 && i <= 40) {
				str = "E";
			}

		}
		return str;
	}

	public Integer getScale_int() {
		Double d = null;
		if (scale != null) {
			d = (Double) scale * 100;
		}
		return d != null ? d.intValue() : null;

	}

}
