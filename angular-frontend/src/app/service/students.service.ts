import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {StudentDto} from "../model/student-dto";

const BASE_URL='http://127.0.0.1:8088/students/';
@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private httpClient:HttpClient) { }


  getStudents(): Observable<StudentDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<StudentDto[]>
  }
}
