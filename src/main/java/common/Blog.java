package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Both in client and the server,
 * simulating the information transferred in RPC
 * simulating 模拟
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Integer id;

    private Integer useId;

    private String title;
}
