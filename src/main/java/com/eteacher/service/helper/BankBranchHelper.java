package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BankBranchHelper {

    private final ActiveUserContext context;

//    @Autowired
//    private final CommonRestClient commonRestClient;

    public void getSaveData(BankBranch bankBranch) {
        bankBranch.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        bankBranch.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(BankBranch bankBranch, RecordStatus status) {
        bankBranch.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        bankBranch.setRecordStatus(status);
        bankBranch.setRecordVersion(bankBranch.getRecordVersion() + 1);
    }

/*    public void BankBranch getAssociatedData(BankBranch bankBranch, BankBranchRequest request) {

        String country = commonRestClient.getCountryById(1L);
        System.out.println("..........................................." );
        System.out.println("coutry: " + country);
        System.out.println("...........................................");
        return bankBranch;
    }*/
}
