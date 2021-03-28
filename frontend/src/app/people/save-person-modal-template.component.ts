import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { PeopleService } from '../core/services/people.service';
import { Person } from '../core/models/person';
import { Subject } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { AlertModalService } from '../core/services/alert-modal.service';

@Component({
  selector: 'app-save-person-modal-content',
  providers: [DatePipe],
  template: `
    <div class="modal-header">
      <h4 class="modal-title pull-left">{{item && item.id ? 'Alterar uma Pessoa' : 'Adicionar uma nova Pessoa'}}</h4>
      <button type="button" class="close pull-right" aria-label="Fechar" (click)="bsModalRef.hide()">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <form [formGroup]="form">
        <div class="row">
          <div class="col-8 pr-1">
            <div class="form-group">
              <label>Nome <span class="text-danger">*</span></label>
              <input type="text" class="form-control" formControlName="name">
            </div>
          </div>
          <div class="col-4 pl-1">
            <div class="form-group">
              <label>CPF <span class="text-danger">*</span></label>
              <input type="text" class="form-control" formControlName="cpf">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-6 pr-1">
            <div class="form-group">
              <label>Date de Nascimento <span class="text-danger">*</span></label>
              <input type="text" class="form-control" formControlName="birthDate">
            </div>
          </div>
          <div class="col-6 pl-1">
            <div class="form-group">
              <label>Sexo</label>
              <select class="form-control" formControlName="gender">
                <option value=""></option>
                <option value="MALE">Masculino</option>
                <option value="FEMALE">Feminino</option>
                <option value="NEUTER">Neutro</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label>E-mail</label>
              <input type="email" class="form-control" formControlName="email">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-6 pr-1">
            <div class="form-group">
              <label>Naturalidade</label>
              <input type="text" class="form-control" formControlName="naturalness">
            </div>
          </div>
          <div class="col-6 pr-1">
            <div class="form-group">
              <label>Nacionalidade</label>
              <input type="text" class="form-control" formControlName="nationality">
            </div>
          </div>
        </div>
        <div class="form-group">
          <label>Endereço <span class="text-danger">*</span></label>
          <input type="text" class="form-control" formControlName="address">
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" (click)="cancel()">Cancelar</button>
      <button type="button" class="btn btn-danger" (click)="save()" [disabled]="isSaving">Salvar</button>
    </div>
  `
})
export class SavePersonModalTemplateComponent implements OnInit {

  public onClose: Subject<boolean> = new Subject<boolean>();

  item = new Person();
  isSaving = false;

  form = this.fb.group({
    name: [''],
    cpf: [''],
    gender: [''],
    email: [''],
    birthDate: ['0000-00-00'],
    naturalness: [''],
    nationality: [''],
    address: ['']
  });

  constructor(public bsModalRef: BsModalRef,
              private fb: FormBuilder,
              private peopleService: PeopleService,
              private alertModalService: AlertModalService) {
  }

  ngOnInit(): void {
    if (this.item && this.item.id) {
      this.form.setValue({
        name: this.item.name,
        cpf: this.item.cpf,
        gender: this.item.gender,
        email: this.item.email,
        birthDate: this.item.birthDate,
        naturalness: this.item.naturalness,
        nationality: this.item.nationality,
        address: this.item.address
      });
    } else {
      const dateNow = new Date();
      const month = dateNow.getMonth() + 1;
      const suggestBirthDate = dateNow.getFullYear() + '-' +
        ((month < 10) ? '0' : '') + month + '-' + dateNow.getDate();

      this.form.patchValue({
        birthDate: suggestBirthDate,
      });
    }
  }

  cancel(): void {
    this.onClose.next(false);
    this.bsModalRef.hide();
  }

  save(): void {
    if (!this.form.valid) {
      this.alertModalService.toastError('Existem um ou mais campos inválido!');
      return;
    }

    const value = this.form.value;
    this.item.name = value.name;
    this.item.cpf = value.cpf;
    this.item.gender = value.gender;
    this.item.email = value.email;
    this.item.birthDate = value.birthDate;
    this.item.naturalness = value.naturalness;
    this.item.nationality = value.nationality;
    this.item.address = value.address;

    this.isSaving = true;
    let observable;

    if (this.item.id) {
      observable = this.peopleService.update(this.item, this.item.id);
    } else {
      observable = this.peopleService.persist(this.item);
    }

    observable.subscribe(
      () => {
        this.alertModalService.toastError('Pessoa "' + this.item.name + '" salva com sucesso!');
        this.onClose.next(true);
        this.bsModalRef.hide();
        this.isSaving = false;
      },
      (data) => {
        let errorMessage;
        if (data.error && data.error.errors) {
          errorMessage = data.error.errors.sort().join('\n');
        } else {
          errorMessage = data.message;
        }
        this.alertModalService.toastError(errorMessage);
        this.isSaving = false;
      });
  }
}
