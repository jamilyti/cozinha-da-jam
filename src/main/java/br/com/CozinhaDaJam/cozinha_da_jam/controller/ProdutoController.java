
package br.com.CozinhaDaJam.cozinha_da_jam.controller;

import br.com.CozinhaDaJam.cozinha_da_jam.entity.Produto;
import br.com.CozinhaDaJam.cozinha_da_jam.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody Produto dadosProduto) {

        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(dadosProduto.getNome());
                    produto.setDescricao(dadosProduto.getDescricao());
                    produto.setPreco(dadosProduto.getPreco());
                    produto.setImagem(dadosProduto.getImagem());
                    produto.setEstoque(dadosProduto.getEstoque());
                    produto.setAtivo(dadosProduto.getAtivo());

                    Produto atualizado = produtoRepository.save(produto);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
