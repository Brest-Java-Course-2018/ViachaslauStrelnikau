import { Component, OnInit } from '@angular/core';
import {StudentDto} from "../../../model/student-dto";
import {StudentsService} from "../../../service/students.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.scss'],
})
export class StudentsListComponent implements OnInit {


  students:StudentDto[]=[];
  loading =false;
  displayedColumns: string[] = ['studentId', 'studentName', 'studentBirth', 'studentAvgMarks','fullName', 'star'];
  constructor(private studentsService:StudentsService) { }

  ngOnInit() {
    this.getStudents();
  }

  getStudents()
  {
    this.loading=true;
    this.studentsService.getStudents().subscribe(data => {
      this.students = data;
      this.loading=false;
    },error1 => this.handleError(error1));
  }
  handleError(err: HttpErrorResponse) {
    console.log('error!');
    let errorMessage: string;

    // A client-side or network error occurred.
    if (err.error instanceof Error) {
      errorMessage = `An error occurred: ${err.error.message}`;
    }
    // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    else {
      errorMessage = `Backend returned code ${err.status}, body was: ${err.error}`;
    }
    this.loading=false;
    console.error(errorMessage);
  }
}
