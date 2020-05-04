import { Sensor } from '../sensor'
import { SensorType } from '../SensorType'
import { Unit } from '../Unit'
import { SensorService } from '../sensor.service'
import { RouterService } from '../../router/router.service'
import { FormGroup, Validators, FormBuilder } from '@angular/forms'
import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-edit-sensor',
  templateUrl: './edit-sensor.component.html',
  styleUrls: ['./edit-sensor.component.css']
})
export class EditSensorComponent implements OnInit {
  id = +localStorage.getItem('updatableId')
  sensor:Sensor
  formGroup: FormGroup
  isValid = false
  types: SensorType[] = []
  units: Unit[] = []
  constructor (private router: RouterService, private sensorSevice: SensorService, private formBuilder: FormBuilder) { }

  ngOnInit (): void {
    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      model: ['', Validators.required],
      from: ['', Validators.required],
      to: ['', [Validators.required]],
      type: ['', Validators.required],
      unit: ['', Validators.required],
      location: [''],
      description: ['']
    })
    this.initSensor()
    this.getUnits()
    this.getTypes()
  }

  back () {
    this.router.toHomeScreen()
  }

  editSensor () {
    this.sensorSevice.update(this.formGroup.value, this.id).subscribe(() => this.back())
  }

  validate () {
    this.isValid = this.formGroup.get('to').value > this.formGroup.get('from').value
  }

  getUnits () {
    this.units = this.sensorSevice.getUnits()
  }

  getTypes () {
    this.types = this.sensorSevice.getTypes()
  }

  initSensor () {
    this.sensorSevice.getById(this.id).subscribe(res => {
      this.sensor = res
      this.formGroup.setValue({
        name: res.name,
        model: res.model,
        from: res.from,
        to: res.to,
        type: this.sensorSevice.typeConverter(res.type),
        unit: this.sensorSevice.unitConverter(res.unit),
        location: res.location,
        description: res.description
      })
    })
  }
}
