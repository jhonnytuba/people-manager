import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PeopleComponent } from './people/people.component';
import { SourceComponent } from './source/source.component';

const routes: Routes = [
  {path: '', redirectTo: 'people', pathMatch: 'full'},
  {path: 'people', pathMatch: 'full', component: PeopleComponent},
  {path: 'source', pathMatch: 'full', component: SourceComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
