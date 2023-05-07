package com.buggycarsrating.qa.pages;

import com.buggycarsrating.qa.driver.WebDriverLoader;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class ModelPage  {


    //Page Factory - Object Repository
    @FindBy(xpath = "//h4[contains(text(),'Votes')]/strong")
    protected WebElement votes;

    @FindBy(id = "comment")
    protected WebElement comment;

    @FindBy(xpath = "//button[contains(text(),'Vote!')]")
    protected WebElement vote;

    @FindBy(xpath = "//a[contains(text(),'View more')]")
    protected WebElement viewMore;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[1]")
    protected WebElement voteDate;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[2]")
    protected WebElement voteAuthor;

    @FindBy(xpath = "//table[@class='table']//tr[1]//td[3]")
    protected WebElement voteComment;

    @FindBy(xpath = "//p[contains(text(),'Thank you for your vote!')]")
    protected WebElement voteSuccessMessage;

    //initialization
    public ModelPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    //Method to  add new vote
    public void vote(String comment) {
        this.comment.sendKeys(comment);
        vote.click();

    }

    //Method to verify vote count after new vote
    public int verifyVoteCount() {
        int voteCountBefore = getVotes();
        int voteCountAfter = voteCountBefore + 1;
        if (voteCountAfter == voteCountBefore + 1) {
            log.info("Vote count correct");
        } else {
            log.info("Vote count invalid");
        }
        return voteCountAfter;
    }

    //Method to click view more
    public void clickViewMore() {
        viewMore.click();
    }

    //Method to get vote count
    public int getVotes() {
        return Integer.parseInt(votes.getText());
    }

    //Method to getTopVoteDate
    public String getTopVoteDate() {
        return voteDate.getText();
    }

    //Method to getTopVoteAuthor
    public String getTopVoteAuthor() {
        return voteAuthor.getText();
    }

    //Method to get Success Message after vote
    public String getSuccessMessage() {
        return voteSuccessMessage.getText();
    }

    //Method to get top vote comment
    public String getTopVoteComment() {
        return voteComment.getText();
    }
}
