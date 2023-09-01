import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddUserRoutingModule } from './add-user-routing.module';
import { AddUserComponent } from './add-user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { SelectButtonModule } from 'primeng/selectbutton';
import { ToolbarModule } from 'primeng/toolbar';

@NgModule({
    declarations: [
        AddUserComponent
    ],
    imports: [
        CommonModule,
        AddUserRoutingModule,
        ReactiveFormsModule,
        TableModule,
        DialogModule,
        ButtonModule,
        ToolbarModule,
        RippleModule,
        SelectButtonModule,
        
        
    ]
})
export class AddUserModule { }
