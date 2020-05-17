package Code.DataBaseProject.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Code.DataBaseProject.Repository.StudentRepositiory;
import Code.DataBaseProject.Repository.SubjectsRepository;
import Code.DataBaseProject.models.Student;
import Code.DataBaseProject.models.Subjects;

@Service
public class StudentSetupService {

	@Autowired
	private StudentRepositiory studentRepository;

	@Autowired
	private SubjectsRepository subjectRepsitory;

	public void saveStudentDetails(Student student) {
		studentRepository.save(student);
	}

	public Student checkIfUserIsUnique(String rollNo) {
		return studentRepository.findStudentByRollNo(rollNo);
	}

	public Student checkIfUserIsPresent(String id) {

		return studentRepository.findUserById(id);
	}

	public void updateEntity(Student student, Student studObject, String id ) {
		studObject.setRollNo(student.getRollNo());
		studObject.setStudentName(student.getStudentName());
		Set<Subjects> subjects = subjectRepsitory.findSubjectsByStudentId(Integer.valueOf(id));
		subjects.removeAll(subjects);
		for (Subjects sub : student.getSubjects()) {
			subjects.add(new Subjects(sub.getSubjectName(), studObject));
		}
		studObject.getSubjects().addAll(subjects);
		this.saveStudentDetails(studObject);

	}

	public void createEntity(Student studentObj, Student student) {
		studentObj.setRollNo(student.getRollNo());
		studentObj.setStudentName(student.getStudentName());
		Set<Subjects> subSet = new LinkedHashSet<Subjects>();
		
		for (Subjects sub : student.getSubjects()) {
			subSet.add(new Subjects(sub.getSubjectName(), studentObj));
		}
		studentObj.getSubjects().addAll(subSet);
		this.saveStudentDetails(studentObj);
	}

	public void deleteEntity(Student studObject) {
		studentRepository.delete(studObject);

	}

}
