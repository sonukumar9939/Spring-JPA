package code.DataBaseProject.models;

import java.util.List;

public class StudentCourseContext {

	private List<Student> studentContext;
	private List<Course> courseContext;

	public List<Student> getStudentContext() {
		return studentContext;
	}

	public void setStudentContext(List<Student> studentContext) {
		this.studentContext = studentContext;
	}

	public List<Course> getCourseContext() {
		return courseContext;
	}

	public void setCourseContext(List<Course> courseContext) {
		this.courseContext = courseContext;
	}

}
