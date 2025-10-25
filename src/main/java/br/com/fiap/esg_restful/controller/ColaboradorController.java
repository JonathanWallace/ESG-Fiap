package br.com.fiap.esg_restful.controller;

import br.com.fiap.esg_restful.model.Colaborador;
import br.com.fiap.esg_restful.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/colaboradores")
    public ResponseEntity listaColaboradores(Pageable paginacao) {
        Page<Colaborador> colaboradores = colaboradorService.listarColaboradores(paginacao);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/colaboradores/{id}")
    public ResponseEntity listaColaboradores(@PathVariable Long id) {
        Colaborador colaborador = colaboradorService.buscarColaboradorPorId(id);
        return ResponseEntity.ok(colaborador);
    }

    @PostMapping("/colaboradores")
    public ResponseEntity salvarColaborador(@RequestBody Colaborador colaborador) {
        Colaborador colaboradorSalvo = colaboradorService.adicionarColaborador(colaborador);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(colaboradorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(colaboradorSalvo);
    }

    @DeleteMapping("/colaboradores/{id}")
    public ResponseEntity deletarColaborador(@PathVariable Long id) {
        colaboradorService.removerColaborador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/colaboradores")
    public ResponseEntity atualizarColaborador(@RequestBody Colaborador colaborador) {
        Colaborador colaboradorSalvo = colaboradorService.atualizarColaborador(colaborador);
        return ResponseEntity.ok(colaboradorSalvo);
    }
}
