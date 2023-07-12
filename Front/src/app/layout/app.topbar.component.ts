import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "./service/app.layout.service";
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;

    constructor(public layoutService: LayoutService,
        public userService: UserService,
        public userAuthService: UserAuthService,
        public router: Router) { }



        public isLoggedIn() {
            return this.userAuthService.isLoggedIn();
          }
        
          public logout() {
            this.userAuthService.clear();
            this.router.navigate(['/auth/login']);
          }
        
          public isAdmin() {
            return this.userAuthService.isAdmin();
        
          }
          public isUser(){
            return this.userAuthService.isUser();
        
          }
}
