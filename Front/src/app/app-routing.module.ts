import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app.layout.component';
import { LoginComponent } from './layout/components/auth-layout/login/login.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    children: [
      { path: "", redirectTo: "/auth/login", pathMatch: "full" },
      { path: 'auth', loadChildren: () => import('./layout/components/auth-layout/auth-layout.module').then(m => m.AuthLayoutModule) },
    ]
  },
  
 
  { path: 'admin', component: AppLayoutComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }, children: [
    { path: 'pages', loadChildren: () => import('./layout/components/admin-pages/admin-pages.module').then(m => m.AdminPagesModule) }
  ] },
  { path: 'user', component: AppLayoutComponent, canActivate: [AuthGuard], data: { roles: ['User'] }, children: [
    { path: 'pages', loadChildren: () => import('./layout/components/user-pages/user-pages.module').then(m => m.UserPagesModule) }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
