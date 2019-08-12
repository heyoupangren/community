package life.cwh.community.cache;

import life.cwh.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Cwh
 * CreateTime 2019/8/10 21:14
 */
@Component
@Data
public class HotTagCache {
    /*private Map<String,Integer> tags =new HashMap<>();*/
    private List<String> hots =new ArrayList<>();
    private Map<String,Integer> numbers =new HashMap<>();
    public void updateTags(Map<String,Integer> tags,Map<String,Integer> tagNums){
        int max =10;
        PriorityQueue<HotTagDTO> priorityQueue =new PriorityQueue<>(max);
        //遍历tags标签数组，将标签名和对应的数值赋值给hotTagDTO，再添加到priorityQueue中
        tags.forEach((name,priority) ->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            //priorityQueue的长度小于3，则直接添加进队列
            if(priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            }
            //priorityQueue的长度大于3，取出最小的元素与hotTagDTO进行比较，
            // hotTagDTO大于队列中最小的元素，再将hotTagDTO的元素放入队列中去
            else {
                HotTagDTO minHot = priorityQueue.peek();
                if(hotTagDTO.compareTo(minHot) > 0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags =new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll !=null){
            sortedTags.add(0,poll.getName());
            poll=priorityQueue.poll();
        }
        hots=sortedTags;
        numbers=tagNums;
        System.out.println(hots);
        System.out.println(numbers);

    }
}
