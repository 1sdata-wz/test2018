package com.estar.civilPlaint;

import com.estar.common.textutil.BaseMatchRule;
import com.estar.common.textutil.TextMatchRule;
import com.estar.common.textutil.TextProcessRuleReader;
import com.estar.common.textutil.TextProcessRuleSet;
import com.estar.judgment.content.JudgmentContent;

public abstract class CivilPlaintReportRule {

	private static final String RULE_FILE = "civilpaint-rule.xml";
	private static final TextProcessRuleSet RULE_SET = TextProcessRuleReader.readFromXml(CivilPlaintReportRule.class, RULE_FILE, new TextProcessRuleSet[]{BaseMatchRule.getRuleSet()});
	
	public static final String INVALID ="无效文书内容";

	public static TextProcessRuleSet getRuleSet() {
		return RULE_SET;
	}
	
	public static TextMatchRule[] getMatchRules() {
		return RULE_SET.getTextMatchRules();
	}
	
	public static TextMatchRule getMatchRuleByName(JudgmentContent content) {
		return getMatchRuleByName(content.getName());
	}
	
	public static TextMatchRule getMatchRuleByName(String name) {
		return TextMatchRule.getByName(RULE_SET.getTextMatchRulesIncludingDisabled(), name);
	}
}
