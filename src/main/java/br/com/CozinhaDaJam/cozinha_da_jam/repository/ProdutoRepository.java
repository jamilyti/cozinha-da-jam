
package br.com.CozinhaDaJam.cozinha_da_jam.repository;

import br.com.CozinhaDaJam.cozinha_da_jam.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
