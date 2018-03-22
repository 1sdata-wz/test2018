package com.estar.civilPlaint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.estar.common.textutil.Content;
import com.estar.common.textutil.ContentFillerType;
import com.estar.common.textutil.ContentSegmentInfo;
import com.estar.common.textutil.TextMatchRule;
import com.estar.common.textutil.TextProcessor;
import com.estar.common.textutil.TextProcessorFactory;
import com.estar.common.textutil.TextUtil;
import com.estar.common.util.HtmlUtil;
import com.estar.judgment.content.JudgmentContent;

/**
 * 提取民事诉状内容
 * @author 王震
 *
 */
public class CivilPlaintProcessor{
	
	private static final TextProcessor INVALID_SPLITER = TextProcessorFactory.getLastMatchTextSegmentor(getMatchRule(CivilPlaintReportRule.INVALID));
	private static final TextProcessor REASON_SPLITER = TextProcessorFactory.getLastMatchTextSegmentor(getMatchRule(JudgmentContent.REASON.getName()));
	private static final TextProcessor APPEAL_SPLITER = TextProcessorFactory.getLastMatchTextSegmentor(getMatchRule(JudgmentContent.APPEAL.getName()));
	private static final TextProcessor CAUSE_SPLITER = TextProcessorFactory.getLastMatchTextSegmentor(getMatchRule(JudgmentContent.CAUSE.getName()));
	private static final TextMatchRule TYPE_SPLIT_RULE = getMatchRule(JudgmentContent.TYPE.getName());
	private static final TextProcessor THIRD_PARTY_SPLITER = TextProcessorFactory.getFirstMatchTextSegmentorFillerBeforeContent(getMatchRule(JudgmentContent.THIRD_PARTY));
	private static final TextProcessor DEFENDANT_SPLITER = TextProcessorFactory.getFirstMatchTextSegmentorFillerBeforeContent(getMatchRule(JudgmentContent.DEFENDANT));
	private static final TextProcessor PLAINTIFF_SPLITER = TextProcessorFactory.getFirstMatchTextSegmentorFillerBeforeContent(getMatchRule(JudgmentContent.PLAINTIFF));
	private static final ContentSegmentInfo[] SEGMENT_INFOS = getSegmentInfos();
	private static final TextProcessor SEGMENTOR = TextProcessorFactory.getContentSegmentor(SEGMENT_INFOS);
	
	@SuppressWarnings("unchecked")
	public static Map<String, Content> doExecute(String text) {
		Map<String, Content> contents = (Map<String, Content>) SEGMENTOR.execute(text).getResult();
		Content plaintiff = Content.getNvl(contents, JudgmentContent.PLAINTIFF.getName());
		String[] types = (String[]) TextUtil.splitPreservingDelimiter(plaintiff.getFillerBeforeContent(), TYPE_SPLIT_RULE);
		Content type;
		if (types.length > 1) {
			plaintiff.modifyFillerBeforeContent(types[2]);
			type = new Content(types[0], types[1], "");
		} else {
			type = new Content(types[0], "", "");
		}
		contents.put(JudgmentContent.TYPE.getName(), type);
		return contents;
	}

	private static ContentSegmentInfo[] getSegmentInfos() {
		List<ContentSegmentInfo> infos = new ArrayList<ContentSegmentInfo>();
		infos.add(new ContentSegmentInfo(CivilPlaintReportRule.INVALID, new TextProcessor[]{INVALID_SPLITER}));
		infos.add(new ContentSegmentInfo(JudgmentContent.REASON.getName(), CivilPlaintReportRule.INVALID, ContentFillerType.BEFORE_CONTENT, REASON_SPLITER));
		infos.add(new ContentSegmentInfo(JudgmentContent.APPEAL.getName(), JudgmentContent.REASON.getName(), ContentFillerType.BEFORE_CONTENT, new TextProcessor[]{APPEAL_SPLITER}));
		infos.add(new ContentSegmentInfo(JudgmentContent.CAUSE.getName(), JudgmentContent.APPEAL.getName(), ContentFillerType.BEFORE_CONTENT, CAUSE_SPLITER));
		infos.add(new ContentSegmentInfo(JudgmentContent.THIRD_PARTY.getName(), JudgmentContent.APPEAL.getName(), ContentFillerType.BEFORE_CONTENT, THIRD_PARTY_SPLITER));
		//infos.add(new ContentSegmentInfo(JudgmentContent.DEFENDANT.getName(), JudgmentContent.THIRD_PARTY.getName(), ContentFillerType.BEFORE_CONTENT, DEFENDANT_SPLITER));
		//infos.add(new ContentSegmentInfo(JudgmentContent.PLAINTIFF.getName(), JudgmentContent.DEFENDANT.getName(), ContentFillerType.BEFORE_CONTENT, PLAINTIFF_SPLITER));
		return infos.toArray(new ContentSegmentInfo[]{});
	}
	
	private static TextMatchRule getMatchRule(JudgmentContent contentId) {
		return getMatchRule(contentId.getName());
	}
	
	private static TextMatchRule getMatchRule(String contentName) {
		return CivilPlaintReportRule.getMatchRuleByName(contentName);
	}
	
	public static void main(String args[]) throws IOException{
		String judgement = getFilestr();
		doExecute(judgement);
		
	}
	
	private static String getFilestr() throws IOException{
		String path = "C:/Users/Administrator/Desktop/1"; // 路径
		String content ="";
		File f = new File(path);
		if (!f.exists()) {
		    System.out.println(path + " not exists");
		    return content;
		}
		
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
		    File fs = fa[i];
		    if (fs.isDirectory()) {
		        System.out.println(fs.getName() + " [目录]");
		    } else {
		    	content = HtmlUtil.removeTagAndSurplusSpace(TxtReader.read(fs));
		   }
		}
		
		return content;
	}
}
