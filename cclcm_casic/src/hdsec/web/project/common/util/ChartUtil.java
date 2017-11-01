package hdsec.web.project.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ChartDeleter;
import org.jfree.chart.servlet.ServletUtilities;

public class ChartUtil {
	public static String generateChartPic(JFreeChart chart, int width, int height, HttpSession session, PrintWriter pw,
			boolean keepInSession) throws Exception {

		String filename;
		try {
			if (session != null) {
				ChartDeleter deleter = (ChartDeleter) session.getAttribute("JFreeChart_Deleter");
				session.removeAttribute("JFreeChart_Deleter");
				session.setAttribute("JFreeChart_Deleter", deleter);
			}
			//  Write the chart image to the temporary directory
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			//If the last parameter is null, the chart is a "one time"-chart and will be deleted after the first serving.
			//If the last parameter is a session object, the chart remains until session time out.
			filename = ServletUtilities.saveChartAsPNG(chart, width, height, info, keepInSession ? session : null);
			//  Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info, true);
			pw.flush();
		} catch (Exception e) {
			throw new Exception("Generate chart picture is failed!", e);
		}
		return filename;
	}
}
