/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import java.util.List;

public interface IAgencyLogic {
    public int countAgencys();
    public List<AgencyEntity> getAgencys();
    public List<AgencyEntity> getAgencys(Integer page, Integer maxRecords);
    public AgencyEntity getAgency(Long id);
    public AgencyEntity createAgency(AgencyEntity entity); 
    public AgencyEntity updateAgency(AgencyEntity entity);
    public void deleteAgency(Long id);
    public List<FAQEntity> listFAQs(Long agencyId);
    public FAQEntity getFAQ(Long agencyId, Long faqId);
    public FAQEntity addFAQ(Long agencyId, Long faqId);
    public List<FAQEntity> replaceFAQs(Long agencyId, List<FAQEntity> list);
    public void removeFAQ(Long agencyId, Long faqId);
}
