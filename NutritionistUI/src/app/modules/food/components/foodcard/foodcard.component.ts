import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Food } from '../../food';
import { MatSnackBar, MatDialog } from '@angular/material';
import { FoodService } from '../../food.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { FooddialogComponent } from '../fooddialog/fooddialog.component';

@Component({
  selector: 'food-foodcard',
  templateUrl: './foodcard.component.html',
  styleUrls: ['./foodcard.component.css']
})
export class FoodcardComponent implements OnInit {
  
  @Input()
  food : Food;

  @Input()
  useFavListApi: boolean;

  @Output()
  addFood= new EventEmitter()

  @Output()
  deleteFood= new EventEmitter()
  
  constructor( private snackBar : MatSnackBar, private dialog : MatDialog, private service : FoodService, private router : Router, private auth:AuthenticationService) { 

    
  }

  ngOnInit() {
  }

  addToFavList(){
    this.addFood.emit(this.food);
    console.log("pressed");
    console.log(this.food);
  }
  deleteFromFavList(){
    this.deleteFood.emit(this.food);
  }
  

  viewDetails(ndbno){
    
    this.service.showDetails(ndbno);
    console.log(ndbno);
    this.router.navigate(['/foods/details']);
  
  }
  
 updateFromFavList(actionType){
    let dialogRef = this.dialog.open(FooddialogComponent,{
     width : "400px",
     data : {
       obj : this.food, actionType : actionType
     }
    });
 
    dialogRef.afterClosed().subscribe(result=>{
      console.log("The dialog was closed");
    });
  }

}
