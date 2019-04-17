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


  getStudents(): Observable<StudentDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<StudentDto[]>;
  }

  getStudent(num:string): Observable<Student>
  {
    let result=this.httpClient.get(BASE_URL+num);
    return result as Observable<Student>;
  }

  removeStudent(num:string)
  {
    return this.httpClient.delete(BASE_URL+num);
  }

  updateGroup(student:Student)
  {
    return this.httpClient.post(BASE_URL+student.studentId,student);
  }

  getFilteredStudents(dateFrom:string,dateTo:string): Observable<StudentDto[]>
  {
    let result=this.httpClient.get(BASE_URL+dateFrom+dateTo);
    return result as Observable<StudentDto[]>;
  }

}
