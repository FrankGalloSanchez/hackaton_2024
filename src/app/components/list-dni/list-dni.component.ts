import { Component, OnInit } from '@angular/core';
import { Dni } from '../../model/dni';
import { DniService } from '../../service/dni.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  standalone: true,
  imports: [HttpClientModule , CommonModule , FormsModule],
  selector: 'app-list-dni',
  templateUrl: './list-dni.component.html',
})
export class ListDniComponent implements OnInit {
  dnis: Dni[] = [];
  editingDni: Dni | null = null; // Registro que se está editando
  statusFilter: string = 'A';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private dniService: DniService) {}

  ngOnInit(): void {
    this.cargarDnis();
  }

  cargarDnis(): void {
    this.dniService.getByStatus(this.statusFilter).subscribe({
      next: (data) => {
        this.dnis = data;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar los DNIs';
        this.successMessage = '';
      }
    });
  }

  restaurarDni(id: number): void {
    this.dniService.restoreDni(id).subscribe({
      next: (data) => {
        this.successMessage = 'DNI restaurado correctamente';
        this.errorMessage = '';
        this.cargarDnis();
      },
      error: (error) => {
        this.errorMessage = 'Error al restaurar el DNI';
        this.successMessage = '';
      }
    });
  }

  editarDni(dni: Dni): void {
    this.editingDni = { ...dni };
  }

  guardarEdicion(): void {
    if (this.editingDni) {
      this.dniService.updateDni(this.editingDni.id, this.editingDni.dni).subscribe({
        next: () => {
          this.successMessage = 'DNI actualizado correctamente';
          this.errorMessage = '';
          this.editingDni = null; // Limpiamos la edición
          this.cargarDnis(); // Recargamos la lista
        },
        error: () => {
          this.errorMessage = 'Error al actualizar el DNI';
          this.successMessage = '';
        },
      });
    }
  }

  cancelarEdicion(): void {
    this.editingDni = null; // Cancelamos la edición
  }

  eliminarLogicamente(id: number): void {
    this.dniService.deleteDni(id).subscribe({
      next: (data) => {
        this.successMessage = 'DNI eliminado lógicamente';
        this.errorMessage = '';
        this.cargarDnis();
      },
      error: (error) => {
        this.errorMessage = 'Error al eliminar el DNI lógicamente';
        this.successMessage = '';
      }
    });
  }

  eliminarFisicamente(id: number): void {
    this.dniService.deleteFisical(id).subscribe({
      next: (data) => {
        this.successMessage = 'DNI eliminado físicamente';
        this.errorMessage = '';
        this.cargarDnis();
      },
      error: (error) => {
        this.errorMessage = 'Error al eliminar físicamente el DNI';
        this.successMessage = '';
      }
    });
  }

  cambiarFiltroEstado(filtro: string): void {
    this.statusFilter = filtro;
    this.cargarDnis();
  }
}
