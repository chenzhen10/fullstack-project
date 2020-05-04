import { AuthGuardService } from './auth/auth-guard.service'
import { EditSensorComponent } from './sensor/edit-sensor/edit-sensor.component'
import { AddSensorComponent } from './sensor/add-sensor/add-sensor.component'
import { SensorComponent } from './sensor/sensor.component'
import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'
import { LoginComponent } from './login/login.component'

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'sensors', component: SensorComponent, canActivate: [AuthGuardService] },
  { path: 'sensors/add', component: AddSensorComponent, canActivate: [AuthGuardService], data: { role: 'ADMIN' } },
  { path: 'sensors/update/:id', component: EditSensorComponent, canActivate: [AuthGuardService], data: { role: 'ADMIN' } }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
