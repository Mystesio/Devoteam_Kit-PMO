import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,NgForm } from '@angular/forms';
import { Role } from 'src/app/_model/role.model';
import { User } from 'src/app/_model/user.model';
import { MessageService } from 'primeng/api';
import { RoleService } from 'src/app/_services/role.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss'],
  providers: [MessageService]
})
export class AddUserComponent implements OnInit {
  productDialog: boolean = false;
  userForm: FormGroup;
  successMessage: string = '';
  roles!: Role[];
  users!: User[];
  user: User={
    userFirstName: '',
    userLastName: '',
    userName: '',
    userPassword: ''
  };


  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private roleService: RoleService
  ) 
  
  
  {
    this.userForm = this.formBuilder.group({
      userFirstName: ['', Validators.required],
      userLastName: ['', Validators.required],
      userName: ['', Validators.required],
      userPassword: ['', Validators.required],
      roleName: ['', Validators.required]
    });
    this.loadRoles();
    this.loadUsers();

  }
  ngOnInit(): void {
  }
  openNew() {
    this.productDialog = true;
  }
  onSubmit() {
    if (this.userForm.invalid) {
      return;
    }

    const user: User = {
      userFirstName: this.userForm.value.userFirstName,
      userLastName: this.userForm.value.userLastName,
      userName: this.userForm.value.userName,
      userPassword: this.userForm.value.userPassword
    };
    const roleName = this.userForm.value.roleName;

    
 
    this.userService.createUserWithRole(user, roleName)
      .subscribe(
        (response) => {
          console.log('User created successfully!', response);
         this.successMessage="user added successfully!";
         this.userForm.reset();
         this.loadUsers();

        },
        (error) => {
          console.error('Error creating user:', error);
        }
      );
  }
  onDeleteUser(user: User) {

    this.userService.deleteUser(user).subscribe(
        () => {
          console.log('user deleted successfully.');
    
        },
        (error: HttpErrorResponse) => {
          console.error('An error occurred while deleting the user:', error);
        }
      );
  }
  loadUsers() {
    this.userService.getAllUsers().subscribe(
      (users: User[]) => {
        this.users = users;
        
      },
      (error) => {
        console.error('Error loading users:', error);
      }
    );
  }
  loadRoles() {
    this.roleService.getAllRoles().subscribe(
      (roles: Role[]) => {
        this.roles = roles;
      },
      (error) => {
        console.error('Error loading roles:', error);
      }
    );
  }
}

