import { Component, OnInit } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AppComponent implements OnInit {

  BASE_URL = "http://localhost:8080/sortEmployeesByColumn"
  sortorders = [SortOrder[SortOrder.NONE], SortOrder[SortOrder.ASC], SortOrder[SortOrder.DSC]]
  sortOrderOfColumns = [SortOrder[SortOrder.NONE], SortOrder[SortOrder.NONE], SortOrder[SortOrder.NONE]]
  title = 'employee-app';
  dataSource: Employee[];
  columnsToDisplay = ['name', 'hireDate', 'salary'];
  expandedElement: Employee | null;
  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get<Employee[]>(this.BASE_URL)
      .subscribe((data: Employee[]) => this.dataSource = data, // success path
        error => console.log(error))
  }
  sortData(index: number) {
    const column = this.columnsToDisplay[index];
    this.sortOrderOfColumns[index] = this.sortorders[(this.sortorders.indexOf(this.sortOrderOfColumns[index]) + 1) % 3]

    let params = new HttpParams();

    params = params.append('column', column);
    params = params.append('sortOrder', this.sortOrderOfColumns[index].toString());
    this.httpClient.get<Employee[]>(this.BASE_URL, 
    { params: this.sortOrderOfColumns[index] != SortOrder[SortOrder.NONE] ? params : null })
      .subscribe((data: Employee[]) => this.dataSource = data, // success path
        error => console.log(error))
  }
}
export interface Employee {
  name: string;
  hireDate: Date;
  salary: number;
  location: string;
}
enum SortOrder {
  ASC, DSC, NONE
}
