package life.cwh.community.schedule;

import life.cwh.community.cache.HotTagCache;
import life.cwh.community.mapper.QuestionMapper;
import life.cwh.community.model.Question;
import life.cwh.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Cwh
 * CreateTime 2019/8/10 16:36
 */
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    //fixedRate:设置定时器的时间间隔
    @Scheduled(fixedRate = 1000*60*60*6)
    //@Scheduled(cron = "0 0 1 * * *")
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 5;
        log.info("The time is hotTagSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        Map<String,Integer> tagNums=new HashMap<>();
        while (offset ==0 || list.size()==limit){
            //查询问题
            list =questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question:list){
                //获取所有问题的tag标签
                String[] tags = StringUtils.split(question.getTag(),",");
                for (String tag:tags){
                    //每个标签tag（Key）对应一个值priority（value）
                    Integer priority = priorities.get(tag);
                    Integer number =tagNums.get(tag);
                    if(priority!=null){
                        //该tag标签不是第一次出现时，原来的priority +5*标签数（1） +问题的回复数 +问题的阅读数
                        priorities.put(tag,priority + 5 + question.getCommentCount() +question.getViewCount());
                        tagNums.put(tag,number + 1);
                    }else {
                        //该tag标签第一次出现时，5*标签数（1） +问题的回复数 +问题的阅读数
                        priorities.put(tag, 5 + question.getCommentCount() +question.getViewCount());
                        tagNums.put(tag,1);
                    }
                }
            }
             offset+=limit;
        }
/*        priorities.forEach(
                (k,v) ->{
                    System.out.print(k);
                    System.out.print(":");
                    System.out.print(v);
                    System.out.println();
        });*/
/*        tagNums.forEach(
                (k,v) ->{
                    System.out.print(k);
                    System.out.print(":");
                    System.out.print(v);
                    System.out.println();
        });*/
        hotTagCache.updateTags(priorities,tagNums);
        log.info("The time is hotTagSchedule end {}", new Date());
    }
}
