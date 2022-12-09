import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GroupCreateComponent } from './group-create/group-create.component';
import { GroupDetailComponent } from './group-detail/group-detail.component';
import { GroupEditComponent } from './group-edit/group-edit.component';
import { GroupListComponent } from './group-list/group-list.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserListComponent } from './user-list/user-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'api/group-list', pathMatch: 'full'},
  { path: 'api/group-list', component: GroupListComponent },
  { path: 'api/user-list', component: UserListComponent},
  { path:'api/group-detail/:id', component:GroupDetailComponent},
  { path:'api/user-detail/:id', component:UserDetailComponent},
  { path:'api/user-edit/:id', component:UserEditComponent},
  { path:'api/group-edit/:id', component:GroupEditComponent},
  { path:'api/group-create', component:GroupCreateComponent},
  { path:'api/user-create', component:UserCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
