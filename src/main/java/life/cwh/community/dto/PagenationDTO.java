package life.cwh.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/6/6 10:56
 */
@Data
public class PagenationDTO<T> {
    private List<T> data;
    private boolean showPrevious; //上一页
    private boolean showFirstPage; //首页
    private boolean showNext;   //下一页
    private boolean showEndPage; //尾页
    private Integer page;   //当前页
    private List<Integer> pages =new ArrayList<>(); //显示的页数
    private Integer totalPage;

    public void setPagenation(Integer totalPage, Integer page) {

        this.totalPage = totalPage;
        this.page =page;
        //展示一定数量的页面码
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i > 0){
                pages.add(0,page-i);
            }
            if(page +i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if(page == 1){
            showPrevious  =false;
        }else{
            showPrevious =true;
        }

        //是否展示下一页
        if(page == totalPage){
            showNext =false;
        }else {
            showNext = true;
        }

        //是否展示首页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //是否展示尾页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
