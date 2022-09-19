package com.loan.app.service.impl;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.entity.LoanOffer;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.LoanCalculations;
import com.loan.app.vo.LoanOfferVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private LoanAppDAO loanAppDAO;

    @Override
    public ApplicationRequestVO createLoanApplication(HashMap applicationFormValue) {
        ApplicationRequestVO applicationRequestVO = getApplicationRequestData(applicationFormValue);
        Application application = new Application();

        application.setApplicationId(applicationRequestVO.getApplicationId());
        application.setCustomerId(applicationRequestVO.getCustomerId());
        application.setCreateDate(applicationRequestVO.getCreateDate());
        application.setUpdateDate(applicationRequestVO.getUpdateDate());

        application.setAccountNumber(applicationRequestVO.getAccountNumber());
        application.setCustomerId(applicationRequestVO.getCustomerId());
        application.setBankName(applicationRequestVO.getBankName());

        application.setAnnualIncome(applicationRequestVO.getAnnualIncome());
        application.setRequestedLoanAmt(applicationRequestVO.getRequestedLoanAmt());
        application.setPanNumber(String.valueOf(applicationRequestVO.getPanNumber()));
        int applicationId = Integer.valueOf(loanAppDAO.saveApplication(application).toString());
        application = loanAppDAO.getApplicationByAppId(applicationId);
        applicationRequestVO.setApplicationId(application.getApplicationId());
        return applicationRequestVO;
    }

    private ApplicationRequestVO getApplicationRequestData(HashMap applicationFormValue) {
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();

        applicationRequestVO.setAccountNumber(Integer.valueOf((String) applicationFormValue.get("accNo")));
        applicationRequestVO.setBankName((String) applicationFormValue.get("bankName"));
        applicationRequestVO.setCustomerId(Integer.valueOf((String) applicationFormValue.get("customerId")));
        applicationRequestVO.setAnnualIncome(Integer.valueOf((String) applicationFormValue.get("annualIncome")));
        applicationRequestVO.setRequestedLoanAmt(Integer.valueOf((String) applicationFormValue.get("requestedLoanAmt")));
        applicationRequestVO.setPanNumber((String) applicationFormValue.get("panNo"));
        applicationRequestVO.setCustomerId(Integer.valueOf((String) applicationFormValue.get("customerId")));
        applicationRequestVO.setUpdateDate(new Date());
        applicationRequestVO.setCreateDate(new Date());
        return applicationRequestVO;
    }

    @Override
    public ApplicationRequestVO viewLoanApplication(String applicationId) {
        Application application = loanAppDAO.getApplicationByAppId(Integer.parseInt(applicationId));
        ApplicationRequestVO applicationRequestVO = setApplicationRequestDataToVO(application);
        return applicationRequestVO;
    }


    private ApplicationRequestVO setApplicationRequestDataToVO(Application application) {
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();

        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setUpdateDate(new Date());
        applicationRequestVO.setCreateDate(new Date());
        applicationRequestVO.setCustomerId(application.getCustomerId());

        applicationRequestVO.setBankName(application.getBankName());
        applicationRequestVO.setPanNumber(application.getPanNumber());
        applicationRequestVO.setAnnualIncome(application.getAnnualIncome());
        applicationRequestVO.setCustomerId(application.getCustomerId());
        applicationRequestVO.setAccountNumber(application.getAccountNumber());

        return applicationRequestVO;
    }

    //todo future implemention
    @Override
    public ApplicationRequestVO deleteLoanApplication(String applicationId) {
        return null;
    }

    @Override
    public void generateOffer(int applicationId) {
        Application application = loanAppDAO.getApplicationByAppId(applicationId);
        Long score = checkCustCibilByPan(application.getPanNumber());
        createLoanOffer(application, score, LoanAppConstant.RELIANCE_PROVIDER);
        createLoanOffer(application, score, LoanAppConstant.BAJAJ_PROVIDER);
    }

    private void createLoanOffer(Application application, Long score, String provider) {
        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setApplicationId(application.getApplicationId());
        loanOffer.setLoanType(application.getProdCode());
        loanOffer.setLoanCreateDate(new Date());
        loanOffer.setTenure(1);
        LoanCalculations loanCalculations = calculateInterestAmount(score, application, provider);
        if (loanCalculations == null || score == 0L) {
            loanOffer.setLoanAmount(0);
            loanOffer.setInterestAmount(0);
            loanOffer.setInterestRate(0);
            loanOffer.setStatus("Rejected");
        } else {
            loanOffer.setLoanAmount(loanCalculations.getSacLoanAmount());
            loanOffer.setInterestAmount(loanCalculations.getCalcInterestAmount());
            loanOffer.setInterestRate(loanCalculations.getIntersetRate());
            loanOffer.setProvider(loanCalculations.getProvider());
            loanOffer.setStatus("Offered");
        }
        loanAppDAO.saveLoanOffer(loanOffer);
    }

    @Override
    public ApplicationAndOfferVO getApplicationAndOfferData(int applicationId) {
        ApplicationAndOfferVO applicationAndOfferVO = new ApplicationAndOfferVO();
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();
        List<LoanOfferVO> loanOfferVOS = new ArrayList();
        Application application = loanAppDAO.getApplicationByAppId(applicationId);
        List<LoanOffer> loanOffers = loanAppDAO.getLoanOfferByApplicationId(applicationId);
        Customer customer = loanAppDAO.getCustomerInfoByCustomerId(application.getCustomerId());

        //set application values
        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setPanNumber(application.getPanNumber());
        applicationRequestVO.setAccountNumber(application.getAccountNumber());
        applicationRequestVO.setAnnualIncome(application.getAnnualIncome());
        applicationRequestVO.setBankName(application.getBankName());
        applicationRequestVO.setCreateDate(application.getCreateDate());

        //set offers value
        if (!ObjectUtils.isEmpty(loanOffers)) {
            for (LoanOffer loanOffer : loanOffers) {
                LoanOfferVO loanOfferVO = new LoanOfferVO();
                loanOfferVO.setLoanAmount(loanOffer.getLoanAmount());
                loanOfferVO.setLoanType(loanOffer.getLoanType());
                loanOfferVO.setTenure(loanOffer.getTenure());
                loanOfferVO.setInterestAmount(loanOffer.getInterestAmount());
                loanOfferVO.setInterestRate(loanOffer.getInterestRate());
                loanOfferVO.setStatus(loanOffer.getStatus());
                loanOfferVO.setProvider(loanOffer.getProvider());
                loanOfferVOS.add(loanOfferVO);
            }
        }
        //set customer value
        applicationAndOfferVO.setFirstName(customer.getFname());
        applicationAndOfferVO.setEmail(customer.getEmailId());
        applicationAndOfferVO.setMiddleName(customer.getMname());
        applicationAndOfferVO.setCustomerId(customer.getCustomerId());
        applicationAndOfferVO.setLastName(customer.getLanme());
        applicationAndOfferVO.setMobileNumber(customer.getContactNumber());

        applicationAndOfferVO.setApplicationRequestVO(applicationRequestVO);
        applicationAndOfferVO.setLoanOfferVOS(loanOfferVOS);
        return applicationAndOfferVO;
    }

    @Override
    public ModelAndView getApplicationAndOfferDetails(ApplicationAndOfferVO applicationAndOfferVO) {
        ModelAndView modelAndView = new ModelAndView("offer");
        if (applicationAndOfferVO != null) {
            modelAndView.addObject("customerobj", applicationAndOfferVO);
            modelAndView.addObject("btnFlag", "hidden");
            modelAndView.addObject("btnVal", "hidden");
        }
        if (applicationAndOfferVO.getApplicationRequestVO() != null) {
            ApplicationRequestVO applicationRequestVO = applicationAndOfferVO.getApplicationRequestVO();
            modelAndView.addObject("applicationobj", applicationRequestVO);
            modelAndView.addObject("btnG", "");
        }
        if (!ObjectUtils.isEmpty(applicationAndOfferVO.getLoanOfferVOS())) {
            List<LoanOfferVO> loanOffers = applicationAndOfferVO.getLoanOfferVOS();
            modelAndView.addObject("loanOffers", loanOffers);
            modelAndView.addObject("btnG", "hidden");
        }
        return modelAndView;
    }

    @Override
    public Application getApplicationByCustomerId(int customerId) {
        return loanAppDAO.getApplicationByCustomerId(customerId);
    }

    @Override
    public ModelAndView getCustomerInfo(Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        ApplicationAndOfferVO applicationAndOfferVO = new ApplicationAndOfferVO();
        applicationAndOfferVO.setCustomerId(customer.getCustomerId());
        applicationAndOfferVO.setFirstName(customer.getFname());
        applicationAndOfferVO.setMiddleName(customer.getMname());
        applicationAndOfferVO.setEmail(customer.getEmailId());
        applicationAndOfferVO.setLastName(customer.getLanme());
        applicationAndOfferVO.setMobileNumber(customer.getContactNumber());
        modelAndView = getApplicationAndOfferDetails(applicationAndOfferVO);
        modelAndView.addObject("btnFlag", "");
        modelAndView.addObject("btnVal", "hidden");
        modelAndView.addObject("btnG", "hidden");
        return modelAndView;
    }

    @Override
    public ModelAndView getAllApplications() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<ApplicationRequestVO> applications = getApplicationData();
        modelAndView.addObject("application", applications);
        modelAndView.addObject("btnFlag", "hidden");
        modelAndView.addObject("btnVal", "");
        modelAndView.addObject("btnG", "hidden");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    private LoanCalculations calculateInterestAmount(Long score, Application application, String provider) {
        int requestedLoanAmt = application.getRequestedLoanAmt();
        int annualIncome = application.getAnnualIncome();
        int loanAmount;
        LoanCalculations loanCalculations = new LoanCalculations();
        if (requestedLoanAmt < (2 * annualIncome)) {
            if (provider.equalsIgnoreCase(LoanAppConstant.RELIANCE_PROVIDER)) {
                if (score > 750) {
                    loanAmount = requestedLoanAmt;
                    loanCalculations.setIntersetRate(8);
                } else if (score > 700) {
                    loanAmount = requestedLoanAmt * 90 / 100;
                    loanCalculations.setIntersetRate(12);
                } else if (score > 650) {
                    loanAmount = requestedLoanAmt * 80 / 100;
                    loanCalculations.setIntersetRate(15);
                } else {
                    loanAmount = requestedLoanAmt * 70 / 100;
                    loanCalculations.setIntersetRate(18);
                }
            } else {
                if (score > 700) {
                    loanAmount = requestedLoanAmt;
                    loanCalculations.setIntersetRate(10);
                } else if (score > 650) {
                    loanAmount = requestedLoanAmt * 90 / 100;
                    loanCalculations.setIntersetRate(15);
                } else if (score > 600) {
                    loanAmount = requestedLoanAmt * 80 / 100;
                    loanCalculations.setIntersetRate(18);
                } else {
                    loanAmount = requestedLoanAmt * 70 / 100;
                    loanCalculations.setIntersetRate(20);
                }
            }
            loanCalculations.setSacLoanAmount(loanAmount);
            loanAmount = loanAmount * loanCalculations.getIntersetRate() / 100;
            loanCalculations.setCalcInterestAmount(loanAmount);
            loanCalculations.setProvider(provider);
            return loanCalculations;
        }
        return null;
    }

    private Long checkCustCibilByPan(String panNumber) {
        try {
            URL url = new URL("http://localhost:3000/cibildetails_" + panNumber);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                return (Long) data_obj.get("score");
            }
        } catch (Exception e) {
            return 0L;
        }
    }

    @Override
    public List<ApplicationRequestVO> getApplicationData() {
        List<ApplicationRequestVO> applicationRequestVOS = new ArrayList<>();
        List<Application> applications = loanAppDAO.getApplications();
        ;

        for (Application application : applications) {
            Customer customer = loanAppDAO.getCustomerInfoByCustomerId(application.getCustomerId());
            ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();
            applicationRequestVO.setGenerateOffer("");
            if (loanAppDAO.getLoanOfferByApplicationId(application.getApplicationId()) != null) {
                applicationRequestVO.setGenerateOffer("disabled");
            }
            applicationRequestVO.setApplicationId(application.getApplicationId());
            applicationRequestVO.setCustomerId(application.getCustomerId());
            applicationRequestVO.setCustomerName(customer.getFname() + " " + customer.getLanme());
            applicationRequestVO.setPanNumber(application.getPanNumber());
            applicationRequestVOS.add(applicationRequestVO);
        }
        return applicationRequestVOS;
    }
}
