package hdsec.web.project.education.action;

public class DeleteTrainingRecordAction extends EducationBaseAction {

	private static final long serialVersionUID = 1L;
	private String course_id = "";

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (!course_id.equals("")) {
			educationService.deleteTrainingRecord(course_id);
			educationService.deleteClassHourRecord(course_id);
		}
		return SUCCESS;
	}

}