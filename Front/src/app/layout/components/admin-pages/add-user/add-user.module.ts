import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AddUserRoutingModule } from './add-user-routing.module';
import { AddUserComponent } from './add-user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';

@NgModule({
    declarations: [
        AddUserComponent
    ],
    imports: [
        CommonModule,
        AddUserRoutingModule,
        ReactiveFormsModule,
        TableModule
        
        
    ]
})
export class AddUserModule { }
