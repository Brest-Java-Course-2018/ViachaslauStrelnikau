import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Student} from "../../../model/student";

@Component({
  selector: 'app-students-edit',
  templateUrl: './students-edit.component.html',
  styleUrls: ['./students-edit.component.scss']
})
export class StudentsEditComponent implements OnInit {

  title;
  constructor(
    public dialogRef: MatDialogRef<StudentsEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Student) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.title=this.data.groupId?'Edit '+this.data.studentId+' record':'Add record';
  }

}
