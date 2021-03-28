import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PeopleComponent } from './people/people.component';

const routes: Routes = [
  {path: '**', redirectTo: 'people', pathMatch: 'full'},
  {path: 'people', pathMatch: 'full', component: PeopleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
