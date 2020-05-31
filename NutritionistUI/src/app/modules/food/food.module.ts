import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContainerComponent } from './components/container/container.component';
import { SearchComponent } from './components/search/search.component';
import { FoodcardComponent } from './components/foodcard/foodcard.component';
import { FooddialogComponent } from './components/fooddialog/fooddialog.component';
import { FooddbComponent } from './components/fooddb/fooddb.component';
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';
import { MatCardModule, MatButtonModule, MatSnackBarModule, MatInputModule } from '@angular/material';
import { FoodService } from './food.service';
import { FoodRouterComponent } from './food.route';
import { FavlistComponent } from './components/favlist/favlist.component';
import { ViewdetailsComponent } from './components/viewdetails/viewdetails.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './interceptor.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [ContainerComponent, 
    SearchComponent, FoodcardComponent, FooddialogComponent, FooddbComponent, FavlistComponent, ViewdetailsComponent],
  imports: [
    CommonModule,
     MatCardModule,
     MatButtonModule,
     FormsModule,
     MatSnackBarModule,MatInputModule, HttpClientModule
   
  ],
  entryComponents:[FooddialogComponent],
  exports :[
    FoodcardComponent, ContainerComponent, FoodRouterComponent
  ],
  providers : [
    FoodService, {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi:true}
  ]
})
export class FoodModule { }
