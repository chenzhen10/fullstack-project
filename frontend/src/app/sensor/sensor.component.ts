import { RouterService } from './../router/router.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SensorService } from './sensor.service';
import { Component, OnInit } from '@angular/core';
import { Sensor } from './sensor';

@Component({
  selector: 'app-sensor',
  templateUrl: './sensor.component.html',
  styleUrls: ['./sensor.component.css']
})
export class SensorComponent implements OnInit {
  
  isAdmin
  show = false
  displayedColumns: string[] = ['name', 'model', 'type', 'range', 'unit', 'location'];
  sensors:Sensor[]
  page: number = 0
  totalPages: number[] = []
  totalElements: number = 0
  formGroup:FormGroup

  constructor(private sensorService: SensorService, private router: RouterService) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      parameter: new FormControl(''),
    })
   this.refresh()
   this.renderColumns()
  }

  renderColumns(){
    this.isAdmin = localStorage.getItem('role') === 'ADMIN'
    if(this.isAdmin){
      this.displayedColumns.push('delete')
      this.displayedColumns.unshift('edit')
    }
  }
  
  refresh(){
    this.sensorService.getAll(this.page).subscribe(result => {
      const sensors = result['content']
      sensors.forEach((val) => {
        val.type = this.sensorService.typeConverter(val.type)
        val.unit = this.sensorService.unitConverter(val.unit)
        this.sensors = sensors
    })
      this.totalPages = new Array(result['totalPages'])
      this.totalElements = result['totalElements']
    })
  }

  setPage(num: number, $event){
    $event.preventDefault()
    this.page = num
    this.refresh()
  }

  logout(){
    this.sensorService.logout()
  }

  search(){
    this.sensorService.search(this.formGroup.value.parameter).subscribe(result => {
      this.sensors = result['content']
      this.totalPages = new Array(result['totalPages'])
      this.totalElements = result['totalElements']  
    })
  }

  delete(id){
    this.sensorService.deleteById(id).subscribe(res =>{
      this.refresh()
    })
  }

  edit(id){
   localStorage.setItem('updatableId', id)
   this.router.toUpdateScreen(id)
  }

  mouseLeave(){
    this.show = false
  }

  mouseEnter(){
    this.show = true
  }

}
