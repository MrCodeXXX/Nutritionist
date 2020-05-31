import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FooddbComponent } from './components/fooddb/fooddb.component';
import { SearchComponent } from './components/search/search.component';
import { ViewdetailsComponent } from './components/viewdetails/viewdetails.component';
import { FavlistComponent } from './components/favlist/favlist.component';
import { AuthGuardService } from 'src/app/auth-gurd.service';


const foodRoutes: Routes = [
    {
        path: 'foods',
        children:[
            { 
                path: '',
                redirectTo : '/foods/search',
               
                pathMatch : 'full',
                canActivate:[AuthGuardService]
                
            },
           
        {
            path : 'search',
            component : SearchComponent,
            canActivate:[AuthGuardService]
            
        },
        {
            path : 'details',
            component : ViewdetailsComponent,
            canActivate:[AuthGuardService]
            
        },
        {
            path : 'fav',
            component : FavlistComponent,
            canActivate:[AuthGuardService]
        }

        ]
    }
];


@NgModule({
    imports: [RouterModule.forChild(foodRoutes)],
    exports: [RouterModule]
  })

export class FoodRouterComponent{}
