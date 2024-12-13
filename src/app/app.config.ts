import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';  // Importa HttpClientModule
import { RouterModule } from '@angular/router';  // Si usas enrutamiento, por ejemplo

export const appConfig = {
  providers: [
    importProvidersFrom(HttpClientModule),
    importProvidersFrom(RouterModule)
  ]
};
