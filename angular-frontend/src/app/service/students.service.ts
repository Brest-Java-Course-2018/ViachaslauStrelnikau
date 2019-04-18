/**
 * StudentsService perform http operations with students
 */
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {StudentDto} from "../model/student-dto";
import {Student} from "../model/student";

const BASE_URL='http://127.0.0.1:8088/students/';
@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private httpClient:HttpClient) { }

  /**
   * gets students list
   */
  getStudents(): Observable<StudentDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<StudentDto[]>;
  }

  /**
   * gets student record by its ID
   * @param id
   */
  getStudent(id:string): Observable<Student>
  {
    let result=this.httpClient.get(BASE_URL+id);
    return result as Observable<Student>;
  }

  /**
   * removes student record from DB
   * @param id student ID
   */
  removeStudent(id:string)
  {
    return this.httpClient.delete(BASE_URL+id);
  }

  /**
   * updates student record in DB
   * @param student Student object
   */
  updateStudent(student:Student)
  {
    return this.httpClient.post(BASE_URL+student.studentId,student);
  }

  /**
   * adds student record to DB
   * @param student Student object
   */
  addStudent(student:Student)
  {
    return this.httpClient.post(BASE_URL,student);
  }

  /**
   * gets list of students by date of birth filter
   * @param dateFrom date from
   * @param dateTo date to
   */
  getFilteredStudents(dateFrom:string,dateTo:string): Observable<StudentDto[]>
  {
    let result=this.httpClient.get(BASE_URL+dateFrom+dateTo);
    return result as Observable<StudentDto[]>;
  }

}
