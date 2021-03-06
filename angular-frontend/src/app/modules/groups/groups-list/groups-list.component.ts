/**
 * logic for group table view
 */
import {Component, OnInit} from '@angular/core';
import {GroupDto} from "../../../model/group-dto";
import {GroupServiceService} from "../../../service/group-service.service";
import {MatDialog} from "@angular/material";
import {DeleteDialogComponent} from "../../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {GroupsEditComponent} from "../groups-edit/groups-edit.component";
import {Group} from "../../../model/group";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-groups-list',
  templateUrl: './groups-list.component.html',
  styleUrls: ['./groups-list.component.scss']
})
export class GroupsListComponent implements OnInit {

  groups: GroupDto[] = [];
  displayedColumns: string[] = ['groupId', 'shortName', 'fullName', 'groupAvgMarks', 'star'];
  title = 'Student management app';
  studentspath = '/students';
  groupspath = '/groups';
  loading = false;

  constructor(private groupService: GroupServiceService, public deleteDialog: MatDialog) {
  }

  ngOnInit() {
    this.getGroups();
  }

  /**
   * gets groups list
   */
  getGroups() {
    this.loading = true;
    this.groupService.getGroups().subscribe(data => {
      this.groups = data;
      this.loading = false;
    }, error1 => this.handleError(error1));
  }

  /**
   * delete group record
   * @param id group ID
   */
  openDeleteDialog(id: string) {
    const dialogRef = this.deleteDialog.open(DeleteDialogComponent, {
      data: id,
      width: '300px',
    });
    dialogRef.afterClosed().subscribe(result => {

      if (result) {
        this.loading = true;
        this.groupService.removeGroup(id).subscribe(() => {
          this.getGroups();
        }, error1 => {
          this.handleError(error1);
        });
      }
    });
  }

  /**
   *  add/edit Group record
   * @param id group id
   */
  openEditDialog(id: string) {

    let groupModel: Group;
    if (id) {
      //edit
      this.loading = true;
      this.groupService.getGroup(id).subscribe(response => {
        groupModel = response;
        this.loading = false;
        const dialogRef = this.deleteDialog.open(GroupsEditComponent, {
          data: groupModel,
          width: '300px',
        });
        dialogRef.afterClosed().subscribe(result => {
          groupModel = result;
          if (groupModel && groupModel.groupId) {
            this.groupService.updateGroup(groupModel).subscribe(() => {
              this.getGroups();
            }, error1 => {
              this.handleError(error1);
            });
          }
        });
      }, error1 => {
        this.handleError(error1);
      });
    } else {
//add
      const dialogRef = this.deleteDialog.open(GroupsEditComponent, {
        data: {},
        width: '300x',
      });
      dialogRef.afterClosed().subscribe(result => {
        groupModel = result;

        if (groupModel && !groupModel.groupId) {
          this.groupService.addGroup(groupModel).subscribe(() => {
            this.getGroups();
          }, error1 => {
            this.handleError(error1);
          });
        }
      }, error1 => {
        this.handleError(error1);
      });
    }
  }

  /**
   * handles http errors
   * @param err  HttpErrorResponse
   */
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
    this.loading = false;
    console.error(errorMessage);
  }
}
