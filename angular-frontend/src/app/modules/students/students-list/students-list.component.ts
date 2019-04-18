import {Component, OnInit, ViewChild} from '@angular/core';
import {StudentDto} from "../../../model/student-dto";
import {StudentsService} from "../../../service/students.service";
import {HttpErrorResponse} from "@angular/common/http";
import {DeleteDialogComponent} from "../../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {MatDialog, MatInput} from "@angular/material";
import {ErrorDialogComponent} from "../../../../../projects/components/src/lib/error-dialog/error-dialog.component";
import {Student} from "../../../model/student";
import {StudentsEditComponent} from "../students-edit/students-edit.component";

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.scss'],
})
export class StudentsListComponent implements OnInit {


  students: StudentDto[] = [];

  displayedColumns: string[] = ['studentId', 'studentName', 'studentBirth', 'studentAvgMarks', 'fullName', 'star'];
  loading = false;
  studentspath = '/students';
  groupspath = '/groups';
  title = 'Student management app';

  @ViewChild('fromInput', {
    read: MatInput
  }) fromInput: MatInput;

  @ViewChild('toInput', {
    read: MatInput
  }) toInput: MatInput;

  constructor(private studentsService: StudentsService,
              public deleteDialog: MatDialog,
              public errorDialog: MatDialog) {
  }

  ngOnInit() {
    this.getStudents();
  }

  /**
   * get students list
   */
  getStudents() {
    this.loading = true;
    this.studentsService.getStudents().subscribe(data => {
      this.loading = false;
      this.students = data;
    }, error1 => {
      this.errorDialogHandle(error1)
    });

  }

  /**
   * delete student record
   * @param id student ID
   */
  openDeleteDialog(id: string) {
    this.loading = true;
    const dialogRef = this.deleteDialog.open(DeleteDialogComponent, {
      data: id,
      width: '300px',
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loading = true;
        this.studentsService.removeStudent(id).subscribe(() => {
          this.loading = false;
          this.getStudents();
        }, error1 => this.errorDialogHandle(error1));
      }
    });
  }

  /**
   * add/edit student record
   * @param id student ID
   */
  openEditDialog(id: string) {
    let studentModel: Student;

    if (id) {
      //edit
      this.loading = true;
      this.studentsService.getStudent(id).subscribe(response => {
        studentModel = response;
        this.loading = false;
        const dialogRef = this.deleteDialog.open(StudentsEditComponent, {
          data: studentModel,
          width: '300px',
        });
        dialogRef.afterClosed().subscribe(result => {
          studentModel = result;
          if (studentModel && studentModel.studentId) {
            this.studentsService.updateStudent(studentModel).subscribe(() => {
              this.loading = false;
              this.getStudents();
            }, error1 => {
              this.errorDialogHandle(error1);
            });
          }
        });
      }, error1 => {
        this.errorDialogHandle(error1);
      });
    } else {
      //add
      const dialogRef = this.deleteDialog.open(StudentsEditComponent, {
        data: {},
        width: '300px',
      });
      dialogRef.afterClosed().subscribe(result => {
        studentModel = result;

        if (studentModel && !studentModel.studentId) {
          this.studentsService.addStudent(studentModel).subscribe(() => {
            this.getStudents();
          }, error1 => {
            this.errorDialogHandle(error1);
          });
        }
      }, error1 => {
        this.errorDialogHandle(error1);
      });
    }
  }

  /**
   * filters students records by birth date interval
   */
  filterList() {
    if (typeof this.fromInput.value == 'undefined' && typeof this.toInput.value == 'undefined') {
      this.deleteDialog.open(ErrorDialogComponent, {
        data: "Input date interval",
        width: '300px',
      });
    } else {
      let strDate1 = '0';
      let strDate2 = '/0';
      if (typeof this.fromInput.value != 'undefined' && this.fromInput.value != null) {
        strDate1 = this.fromInput.value.valueOf();
      }
      if (typeof this.toInput.value != 'undefined' && this.toInput.value != null) {
        strDate2 = '/' + this.toInput.value.valueOf();
      }
      this.studentsService.getFilteredStudents(strDate1, strDate2).subscribe(data => {
        this.students = data;
      }, error1 => {
        this.errorDialogHandle(error1)
      });
    }
  }

  /**
   * clears date filter
   */
  clearFilter() {
    this.fromInput.value = '';
    this.toInput.value = '';
    this.getStudents();
  }

  /**
   * handles http errors
   * @param error HttpErrorResponse
   */
  errorDialogHandle(error: HttpErrorResponse) {
    this.loading = false;
    console.error(error.message);
    this.errorDialog.open(ErrorDialogComponent, {
      data: error.message,
      width: '300px',
    });
  }
}
