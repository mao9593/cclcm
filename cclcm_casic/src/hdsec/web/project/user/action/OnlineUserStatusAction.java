package hdsec.web.project.user.action;

import hdsec.web.project.common.util.ChartUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.service.UserManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;

public class OnlineUserStatusAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String online_user_chart_filename = "";
	
	public String getOnline_user_chart_filename() {
		return online_user_chart_filename;
	}
	
	public void setOnline_user_chart_filename(String online_user_chart_filename) {
		this.online_user_chart_filename = online_user_chart_filename;
	}
	
	@Override
	public String executeFunction() throws Exception {
		DefaultValueDataset dataset = new DefaultValueDataset(UserManager.getOnlineUserIDList().size());
		MeterPlot meterplot = new MeterPlot(dataset);
		meterplot.setRange(new Range(0.0D, Constants.criticalValue));
		meterplot.setInsets(new RectangleInsets(5D, 5D, 5D, 5D), true);
		meterplot.addInterval(new MeterInterval("正常", new Range(0.0D, Constants.normalValue), Color.lightGray,
				new BasicStroke(2.0F), new Color(0, 255, 0, 64)));
		meterplot.addInterval(new MeterInterval("高负荷", new Range(Constants.normalValue, Constants.warningValue),
				Color.lightGray, new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
		meterplot.addInterval(new MeterInterval("危险", new Range(Constants.warningValue, Constants.criticalValue),
				Color.lightGray, new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
		meterplot.setNeedlePaint(Color.black);
		meterplot.setDialBackgroundPaint(Color.white);
		meterplot.setDialOutlinePaint(Color.gray);
		meterplot.setOutlineStroke(new BasicStroke(15f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		meterplot.setDialShape(DialShape.PIE);
		meterplot.setMeterAngle(180);
		meterplot.setUnits("用户");
		meterplot.setTickLabelsVisible(true);
		meterplot.setTickLabelFont(new Font("Dialog", 1, 16));
		meterplot.setTickLabelPaint(Color.blue);
		meterplot.setTickSize(10D);
		meterplot.setTickPaint(Color.blue);
		meterplot.setValuePaint(Color.black);
		meterplot.setValueFont(new Font("Dialog", 1, 16));
		JFreeChart chart = new JFreeChart("在线人数图", JFreeChart.DEFAULT_TITLE_FONT, meterplot, true);
		chart.setBackgroundPaint(Color.white);
		String p = ChartUtil.generateChartPic(chart, 700, 450, getSession(), getResponse().getWriter(), false);
		logger.debug("online_user_chart_filename is :" + p);
		setOnline_user_chart_filename(p);
		return SUCCESS;
	}
	
}
