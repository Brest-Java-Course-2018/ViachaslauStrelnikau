import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {Student} from "../../../model/student";
import {GroupServiceService} from "../../../service/group-service.service";
import {GroupDtoLite} from "../../../model/group-dto-lite";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorDialogComponent} from "../../../../../projects/components/src/lib/error-dialog/error-dialog.component";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-students-edit',
  templateUrl: './students-edit.component.html',
  styleUrls: ['./students-edit.component.scss']
})
export class StudentsEditComponent implements OnInit {

  title;
  loading = false;
  groupsList: GroupDtoLite[] = [];
  constructor(
    public dialogRef: MatDialogRef<StudentsEditComponent>,
    public errorDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: Student,
    private groupsService: GroupServiceService) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.getGroupsList();
    this.title=this.data.groupId?'Edit '+this.data.studentId+' record':'Add record';
  }

  getGroupsList() {
    this.loading = true;
    this.groupsService.getGroupsList().subscribe(data => {
      this.groupsList = data;
      console.log(this.groupsList);
    }, error1 => {
      this.errorDialogHandle(error1)
    });
    this.loading = false;
  }

  errorDialogHandle(error: HttpErrorResponse) {
    this.loading = false;
    console.error(error.message);
    this.errorDialog.open(ErrorDialogComponent, {
      data: error.message,
      width: '300px',
    });
  }
}
