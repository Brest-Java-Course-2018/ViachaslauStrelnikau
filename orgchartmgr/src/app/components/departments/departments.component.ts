import {Component, OnInit} from '@angular/core';
import {DepartmentService} from "../../services/department.service";

@Component({
 selector: 'app-departments',
 templateUrl: './departments.component.html',
 styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit {

 constructor(private departmentService: DepartmentService) {
 }

 ngOnInit() {
   this.getDepartmentDTOs();
 }

 getDepartmentDTOs() {
   this.departmentService.getDepartmentDTOs()
     .subscribe(
       data => console.log(JSON.stringify(data)),
       error => console.log('Server error')
     )
 }
}
