import { RouterModule, Routes } from '@angular/router';
import { EnterpriseListComponent } from './enterprise-list/enterprise-list.component';
import { CreateEnterpriseComponent } from './create-enterprise/create-enterprise.component';
import { SearchEnterpriseComponent } from './search-enterprise/search-enterprise.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  { path: '', redirectTo: 'enterprise', pathMatch: 'full' },
  { path: 'empresa', component: EnterpriseListComponent },
  { path: 'adicionar', component: CreateEnterpriseComponent },
  { path: 'buscar-empresa', component: SearchEnterpriseComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}