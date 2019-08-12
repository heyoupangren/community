package life.cwh.community.dto;

import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/8/10 23:45
 */
@Data
public class HotTagDTO implements Comparable {
 private String name;
 private Integer priority;
 private Integer number;

 @Override
 public int compareTo(Object o) {
  return this.getPriority() - ((HotTagDTO) o).getPriority();
 }
}
