import { SensorType } from '../SensorType'
import { Unit } from '../Unit'
import { SensorService } from '../sensor.service'
import { RouterService } from '../../router/router.service'
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms'
import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-add-sensor',
  templateUrl: './add-sensor.component.html',
  styleUrls: ['./add-sensor.component.css']
})
export class AddSensorComponent implements OnInit {
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
    this.getUnits()
    this.getTypes()
  }

  back () {
    this.router.toHomeScreen()
  }

  addSensor () {
    this.sensorSevice.create(this.formGroup.value).subscribe(() => this.back())
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
}
