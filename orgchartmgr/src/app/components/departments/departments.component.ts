import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";

import {DepartmentService} from "../../services/department.service";
import {DepartmentDto} from "../../model/department-dto";

@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit {

  dtos: Observable<Array<DepartmentDto>>;

  constructor(private departmentService: DepartmentService) {
  }

  ngOnInit() {
    this.dtos = this.getDepartmentDTOs();
  }

  getDepartmentDTOs() {
    return this.departmentService.getDepartmentDTOs();
  }
}

