import { Component, OnInit, Inject } from '@angular/core';
import { Food } from '../../food';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FoodService } from '../../food.service';

@Component({
  selector: 'food-fooddialog',
  templateUrl: './fooddialog.component.html',
  styleUrls: ['./fooddialog.component.css']
})
export class FooddialogComponent implements OnInit {
  food : Food;
  name : string;
  actionType : string;
  constructor(public snackBar : MatSnackBar, public dialogRef: MatDialogRef<FooddialogComponent>,
    @Inject(  MAT_DIALOG_DATA) public data : any, private foodService : FoodService) { 
      this.name=data.obj.name;
      this.food=data.obj;
      this.actionType=data.actionType;
    }

  ngOnInit() {
  }
  onNoClick() {
    this.dialogRef.close();
  }

  updateComments(){
    console.log("name", this.name);
    this.food.name=this.name;
    this.dialogRef.close();
    this.foodService.updateFood(this.food).subscribe(food=>{
      this.snackBar.open("movie updated to WatchList successfully", "", {
        duration : 2000
      })
    })

  }

}
