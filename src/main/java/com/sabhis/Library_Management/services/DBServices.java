package com.sabhis.Library_Management.services;

import com.sabhis.Library_Management.models.AuthorDetails;
import com.sabhis.Library_Management.models.BookDetails;
import com.sabhis.Library_Management.models.BookLoanStatus;
import com.sabhis.Library_Management.models.CmnUserDetails;
import com.sabhis.Library_Management.models.MasterLoanStatus;
import com.sabhis.Library_Management.models.MasterRole;
import com.sabhis.Library_Management.models.MasterStatus;
import com.sabhis.Library_Management.models.PublisherDetails;
import com.sabhis.Library_Management.utils.CommonUtil;
import com.sabhis.Library_Management.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import org.hibernate.Transaction;

public class DBServices {

    public static Date updateDateObject(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();

    }

    public static CmnUserDetails doauth(CmnUserDetails cud) {
        CmnUserDetails returnCud = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.emailId=:emailId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("emailId", cud.getEmailId());
            List<CmnUserDetails> listCud = query.list();
            session.close();
            for (CmnUserDetails cudData : listCud) {
                if (CommonUtil.hashToPlainPassword(cudData.getPassword()).equals(cud.getPassword())) {
                    returnCud = cudData;
                }
            }
            session.close();
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

        return returnCud;
    }

    public static JSONObject fetchMappedBook(int userId) {
        JSONObject object = new JSONObject();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM BookLoanStatus c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
            Query query = session.createQuery(hql);
            query.setParameter("userId", new CmnUserDetails(userId));
            List<BookLoanStatus> listBls = query.list();
            session.close();
            JSONArray jsonBorrowedArray = new JSONArray();
            JSONArray jsonRequestedArray = new JSONArray();
            int countForReturnBook = 0;
            int countForBorrowedBook = 0;
            int countForRequestedBook = 0;
            int countOfBookAlloted = 0;
            for (BookLoanStatus bls : listBls) {
                if (bls.getLoanStatusId().getLoanStatusId() == 1) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                    jsonObject.put("publishedYear", bls.getBookId().getPublishedYear().toString());
                    jsonObject.put("bookLoanId", bls.getBookLoanId());
                    jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                    jsonObject.put("bookLoadStatusId", bls.getLoanStatusId().getLoanStatusId());
                    jsonObject.put("bookLoadStatusName", bls.getLoanStatusId().getStatusName());
                    jsonObject.put("bookBorrowedOn", bls.getOnModified() + "");
                    jsonObject.put("dueDate", bls.getDueDate() + "");
                    jsonBorrowedArray.add(jsonObject);
                    countForBorrowedBook++;
                    countOfBookAlloted++;
                } else if (bls.getLoanStatusId().getLoanStatusId() == 2) {
                    countForReturnBook++;
                } else if (bls.getLoanStatusId().getLoanStatusId() == 3) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                    jsonObject.put("publishedYear", bls.getBookId().getPublishedYear().toString());
                    jsonObject.put("bookLoanId", bls.getBookLoanId());
                    jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                    jsonObject.put("bookLoadStatusId", bls.getLoanStatusId().getLoanStatusId());
                    jsonObject.put("bookLoadStatusName", bls.getLoanStatusId().getStatusName());
                    jsonObject.put("bookRequestedOn", bls.getOnModified() + "");
                    jsonRequestedArray.add(jsonObject);
                    countForRequestedBook++;
                } else {

//                    countOfBookAlloted++;
                }
            }
            object.put("totalBookAllot", countOfBookAlloted);
            object.put("totalBorrowedBookList", jsonBorrowedArray);
            object.put("totalRequestedBookList", jsonRequestedArray);
            object.put("totalBorrowedBook", countForBorrowedBook);
            object.put("totalRequestedBook", countForRequestedBook);
            object.put("totalReturnedBook", countForReturnBook);

            object.put("responseCode", 1);

            System.out.println(listBls);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        }
        return object;
    }

    public static JSONObject fetchAllBooks(String searchKey) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM BookDetails c where c.isEnabled=1 and c.isDeleted=0 and c.bookTitle like :searchKey";
            Query query = session.createQuery(hql);
            query.setParameter("searchKey", "%" + searchKey + "%");
            List<BookDetails> listBd = query.list();
            JSONArray jsonBook = new JSONArray();
            for (BookDetails bd : listBd) {
//                if (bd.getStatusId().getStatusId() != 1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bd.getBookId());
                jsonObject.put("bookStatus", bd.getIsEnabled());
                jsonObject.put("bookName", bd.getBookTitle());
                jsonObject.put("bookAuthorName", bd.getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bd.getTotalPage());
                jsonObject.put("publishedYear", bd.getPublishedYear().toString());
                jsonObject.put("publishedBy", bd.getPublisherId().getPublisherName());
                jsonObject.put("description", bd.getDescriptiion());
                if (bd.getStatusId().getStatusId() == 1) {
                    hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.loanStatusId=:loanStatusId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
                    query = session.createQuery(hql);
                    query.setParameter("bookId", bd);
                    query.setParameter("loanStatusId", new MasterLoanStatus(1));
                    List<BookLoanStatus> listBls = query.list();

                    JSONObject jsonBls = new JSONObject();

                    if (!listBls.isEmpty()) {
                        BookLoanStatus bls = listBls.get(listBls.size() - 1);
                        jsonBls.put("userId", bls.getUserId().getUserId());
                        jsonBls.put("userName", bls.getUserId().getName());
                        jsonBls.put("emailId", bls.getUserId().getEmailId());
                        jsonBls.put("statusName", bls.getLoanStatusId().getStatusName());
                        jsonBls.put("loanStatusId", bls.getLoanStatusId().getLoanStatusId());
                    }
                    jsonObject.put("loanDetails", jsonBls);
                }
                if (bd.getStatusId().getStatusId() == 2) {
                    hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.loanStatusId=:loanStatusId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
                    query = session.createQuery(hql);
                    query.setParameter("bookId", bd);
                    query.setParameter("loanStatusId", new MasterLoanStatus(3));
                    List<BookLoanStatus> listBls = query.list();
//                    BookLoanStatus bls = listBls.get(listBls.size() - 1);
                    JSONArray jsonBlsArray = new JSONArray();
                    for (BookLoanStatus bls : listBls) {
                        JSONObject jsonBls = new JSONObject();
                        jsonBls.put("userId", bls.getUserId().getUserId());
                        jsonBls.put("userName", bls.getUserId().getName());
                        jsonBls.put("emailId", bls.getUserId().getEmailId());
                        jsonBls.put("statusName", bls.getLoanStatusId().getStatusName());
                        jsonBls.put("loanStatusId", bls.getLoanStatusId().getLoanStatusId());
                        jsonBlsArray.add(jsonBls);
                    }
                    jsonObject.put("loanDetails", jsonBlsArray);

                }
                jsonObject.put("bookStatusId", bd.getStatusId().getStatusId());
                jsonObject.put("bookStatusName", bd.getStatusId().getStatusName());
                jsonBook.add(jsonObject);
//                }
            }
            object.put("totalRecord", listBd.size());
            object.put("allAvailableBook", jsonBook);
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchRequestedBookByUserId(int userId) {
        JSONObject object = new JSONObject();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM BookLoanStatus c where c.userId=:userId c.isEnabled=1 and c.isDeleted=0 and c.loanStatusId=:loanStatusId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            List<BookLoanStatus> listBls = query.list();
            session.close();
            object.put("totalBookTaken", listBls.size());
            JSONArray jsonBorrowedArray = new JSONArray();
            int countForReturnBook = 0;
            int countForBorrowedBook = 0;
            for (BookLoanStatus bls : listBls) {
                if (bls.getLoanStatusId().getLoanStatusId() == 1) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookLoanId", bls.getBookLoanId());
                    jsonObject.put("bookLoadStatusId", bls.getLoanStatusId().getLoanStatusId());
                    jsonObject.put("bookLoadStatusName", bls.getLoanStatusId().getStatusName());
                    jsonBorrowedArray.add(jsonObject);
                    countForBorrowedBook++;
                } else {
                    countForReturnBook++;
                }
            }
            object.put("totalBorrowedBookList", jsonBorrowedArray);
            object.put("totalBorrowedBook", countForBorrowedBook);
            object.put("totalReturnedBook", countForReturnBook);

            object.put("responseCode", 1);

            System.out.println(listBls);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        }
        return object;
    }

    public static JSONObject fetchUserProfile(int userId) {
        JSONObject object = new JSONObject();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("userId", userId);
            CmnUserDetails cud = (CmnUserDetails) query.uniqueResult();
            session.close();
            JSONObject userObject = new JSONObject();

            userObject.put("name", cud.getName());
            userObject.put("emailId", cud.getEmailId());
            userObject.put("about", cud.getAbout());
            userObject.put("zipCode", cud.getZipcode());
            userObject.put("city", cud.getCity());
            userObject.put("country", cud.getCounty());
            userObject.put("dob", cud.getDateOfBirth() + "");
            userObject.put("gender", cud.getGender());
            userObject.put("imagePath", cud.getImagePath());

            object.put("user", userObject);
            object.put("responseCode", 1);

        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        }

        return object;
    }

    public static CmnUserDetails updateProfile(CmnUserDetails cud, int userId) {
        CmnUserDetails returnCud = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("userId", userId);
            returnCud = (CmnUserDetails) query.uniqueResult();

            returnCud.setEmailId(cud.getEmailId());
            returnCud.setName(cud.getName());
            returnCud.setCounty(cud.getCounty());
            returnCud.setCity(cud.getCity());
            returnCud.setDateOfBirth(cud.getDateOfBirth());
            returnCud.setAbout(cud.getAbout());
            returnCud.setGender(cud.getGender());
            returnCud.setZipcode(cud.getZipcode());
            session.update(returnCud);
            session.getTransaction().commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

        return returnCud;
    }

    public static int deactivateAccount(int userId) {
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            String hql = "SELECT c FROM BookLoanStatus c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0 and c.loanStatusId=:loanStatusId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            List<BookLoanStatus> listBls = query.list();
            if (listBls.size() > 0) {
                returnData = 2;
            } else {
                hql = "UPDATE BookLoanStatus c SET c.isEnabled=0 WHERE userId=:userId";
                query = session.createQuery(hql);
                query.setParameter("userId", new CmnUserDetails(userId));
                query.executeUpdate();

                hql = "UPDATE CmnUserDetails c SET c.isEnabled=0 WHERE userId=:userId";
                query = session.createQuery(hql);
                query.setParameter("userId", userId);
                query.executeUpdate();

                returnData = 1;
                tx.commit();
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex);
            returnData = 3;

        } finally {
            session.close();
        }

        return returnData;
    }

    public static JSONObject bookOperation(int userId, int bookId, int operation) {
        JSONObject object = new JSONObject();
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            if (operation == 1) {
                object.put("responseCode", 1);
                object.put("msg", "Working On Api");
            } else if (operation == 2) {
                return DBServices.returnBook(userId, bookId);
            } else if (operation == 3) {
                return DBServices.borrowBook(userId, bookId);
            } else if (operation == 4) {
                return DBServices.cancelBook(userId, bookId);
            } else {
                object.put("responseCode", 0);
                object.put("msg", "No Operation Exits,");
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.getMessage());
            object.put("responseCode", 0);
            object.put("msg", "Some Error Occured. Please try after some time.");

        } finally {
            session.close();
        }

        return object;
    }

    private static JSONObject returnBook(int userId, int bookId) {
        JSONObject object = new JSONObject();
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

//            String hql = "SELECT c FROM BookDetails c where c.statusId!=:statusId and c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0 ";
//            Query query = session.createQuery(hql);
//            query.setParameter("statusId", new MasterStatus(1));
//            query.setParameter("bookId", bookId);
//            BookDetails bd = (BookDetails) query.uniqueResult();
//
//            if (bd != null) {
//                object.put("responseCode", 3);
//                object.put("msg", "Book is not in loan!");
//            } else {
            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:statusId1 and c.bookId=:bookId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0  and c.isCanceled=:isCanceled ";

            Query query = session.createQuery(hql);
            query.setParameter("statusId1", new MasterLoanStatus(1));
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("isCanceled", false);
            List<BookLoanStatus> listBls = query.list();

            if (listBls.isEmpty()) {
                object.put("responseCode", 3);
                object.put("msg", "Book is not in loan By User!");
            } else {

                hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:statusId1 and c.bookId=:bookId and c.userId!=:userId and c.isEnabled=1 and c.isDeleted=0  and c.isCanceled=:isCanceled ";
                query = session.createQuery(hql);
                query.setParameter("statusId1", new MasterLoanStatus(3));
                query.setParameter("bookId", new BookDetails(bookId));
                query.setParameter("userId", new CmnUserDetails(userId));
                query.setParameter("isCanceled", false);
                List<BookLoanStatus> listBlsOther = query.list();
                tx = session.beginTransaction();

                hql = "UPDATE BookDetails c SET c.statusId=:statusId WHERE c.bookId=:bookId";
                query = session.createQuery(hql);
                query.setParameter("bookId", bookId);
                if (listBlsOther.isEmpty()) {
                    query.setParameter("statusId", new MasterStatus(3));
                } else {
                    query.setParameter("statusId", new MasterStatus(2));
                }

                query.executeUpdate();

                BookLoanStatus bls = listBls.get(listBls.size() - 1);
//                    BookLoanStatus bls = new BookLoanStatus();
//                    bls.setBookId(new BookDetails(bookId));
//                    bls.setOnCreated(new Date());
                bls.setOnModified(new Date());
//                    bls.setUserId(new CmnUserDetails(userId));
                bls.setLoanStatusId(new MasterLoanStatus(2));
//                    bls.setDescription("No Description as of Now ");
                bls.setIsDeleted(false);
                bls.setIsEnabled(true);
                session.saveOrUpdate(bls);

                tx.commit();
                object.put("responseCode", 1);
                object.put("msg", "Book is Returned to Admin  by you successfully.");

            }

//            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.getMessage());
            object.put("responseCode", 0);
            object.put("msg", "Some Error Occured. Please try after some time.");

        } finally {
            session.close();
        }

        return object;
    }

    private static JSONObject borrowBook(int userId, int bookId) {
        JSONObject object = new JSONObject();
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            String hql = "SELECT c FROM BookLoanStatus c where (c.loanStatusId=:statusId1 or c.loanStatusId=:statusId2) and c.bookId=:bookId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=:isCanceled ";

            Query query = session.createQuery(hql);
            query.setParameter("statusId1", new MasterLoanStatus(1));
            query.setParameter("statusId2", new MasterLoanStatus(3));
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("isCanceled", false);
            List<BookLoanStatus> listBls = query.list();

            if (listBls.size() > 0) {
                object.put("responseCode", 3);
                object.put("msg", "Book is already assigned to you or requested by you");
            } else {
                tx = session.beginTransaction();

                hql = "SELECT c FROM BookLoanStatus c where (c.loanStatusId=:statusId1 or c.loanStatusId=:statusId2) and c.bookId=:bookId and c.userId!=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=:isCanceled ";
                query = session.createQuery(hql);
                query.setParameter("statusId1", new MasterLoanStatus(1));
                query.setParameter("statusId2", new MasterLoanStatus(3));
                query.setParameter("bookId", new BookDetails(bookId));
                query.setParameter("userId", new CmnUserDetails(userId));
                query.setParameter("isCanceled", false);
                List<BookLoanStatus> listBlsOther = query.list();

                if (listBlsOther.isEmpty()) {
                    hql = "UPDATE BookDetails c SET c.statusId=:statusId WHERE c.bookId=:bookId";
                    query = session.createQuery(hql);
                    query.setParameter("bookId", bookId);
                    query.setParameter("statusId", new MasterStatus(2));
                    query.executeUpdate();
                }

                BookLoanStatus bls = new BookLoanStatus();
                bls.setBookId(new BookDetails(bookId));
                bls.setOnCreated(new Date());
                bls.setOnModified(new Date());
                bls.setUserId(new CmnUserDetails(userId));
                bls.setLoanStatusId(new MasterLoanStatus(3));
                bls.setDescription("No Description as of Now ");
                bls.setIsDeleted(false);
                bls.setIsEnabled(true);
                bls.setIsCanceled(false);

                session.save(bls);
                tx.commit();
                object.put("responseCode", 1);
                object.put("msg", "Book is Request by you successfully Admin will approve the request");

            }

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.getMessage());
            object.put("responseCode", 0);
            object.put("msg", "Some Error Occured. Please try after some time.");

        } finally {
            session.close();
        }

        return object;
    }

    private static JSONObject cancelBook(int userId, int bookId) {
        JSONObject object = new JSONObject();
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId and c.bookId=:bookId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=:isCanceled ";

            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
//            query.setParameter("statusId2", new MasterLoanStatus(3));
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("isCanceled", false);
            List<BookLoanStatus> listBls = query.list();
            System.out.println(listBls);
//            if (!listBls.isEmpty()) {
//                object.put("responseCode", 3);
//                object.put("msg", "Book is already removed from rquest list");
//            } else {
            tx = session.beginTransaction();
            for (BookLoanStatus bls : listBls) {
                bls.setLoanStatusId(new MasterLoanStatus(4));
                bls.setIsCanceled(true);
                session.saveOrUpdate(bls);
            }

            hql = "SELECT c FROM BookLoanStatus c where (c.loanStatusId=:loanStatusId1 or c.loanStatusId=:loanStatusId2) and c.bookId=:bookId and c.userId!=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=:isCanceled ";
            query = session.createQuery(hql);
            query.setParameter("loanStatusId1", new MasterLoanStatus(3));
            query.setParameter("loanStatusId2", new MasterLoanStatus(1));
//            query.setParameter("statusId2", new MasterLoanStatus(3));
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("isCanceled", false);
            List<BookLoanStatus> listBlsOther = query.list();
            System.out.println(listBlsOther);
            if (listBlsOther.isEmpty()) {
                hql = "SELECT c FROM BookDetails c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
                query = session.createQuery(hql);
                query.setParameter("bookId", bookId);

                BookDetails bd = (BookDetails) query.uniqueResult();
                bd.setStatusId(new MasterStatus(3));
                session.save(bd);
            }
//                hql = "UPDATE BookLoanStatus c SET c.loanStatusId=:loanStatusId, c.isCanceled=:isCanceled WHERE c.bookId=:bookId and c.bookLoanId=:bookLoanId";
//                query = session.createQuery(hql);
//                query.setParameter("loanStatusId", new MasterLoanStatus(4));
//                query.setParameter("isCanceled", true);
//                
//                query.setParameter("bookId", new BookDetails(bookId));
//                query.executeUpdate();

//                BookLoanStatus bls = new BookLoanStatus();
//                bls.setBookId(new BookDetails(bookId));
//                bls.setOnCreated(new Date());
//                bls.setOnModified(new Date());
//                bls.setUserId(new CmnUserDetails(userId));
//                bls.setLoanStatusId(new MasterLoanStatus(3));
//                bls.setDescription("No Description as of Now ");
//                bls.setIsDeleted(false);
//                bls.setIsEnabled(true);
//
//                session.save(bls);
            tx.commit();
            object.put("responseCode", 1);
            object.put("msg", "Book is removed from request list");

//            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.getMessage());
            object.put("responseCode", 0);
            object.put("msg", "Some Error Occured. Please try after some time.");

        } finally {
            session.close();
        }

        return object;
    }

    public static JSONObject fetchAllBooksForUser(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            String hql = "SELECT c FROM BookDetails c where c.statusId!=:statusId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("statusId", new MasterStatus(1));
            List<BookDetails> listBd = query.list();
            JSONArray jsonBook = new JSONArray();
            for (BookDetails bd : listBd) {
                hql = "SELECT c FROM BookLoanStatus c where (c.loanStatusId=:loanStatusId1 or c.loanStatusId=:loanStatusId2) and c.userId=:userId and c.bookId=:bookId and  c.isEnabled=1 and c.isDeleted=0";
                query = session.createQuery(hql);
                query.setParameter("loanStatusId1", new MasterLoanStatus(3));
                query.setParameter("loanStatusId2", new MasterLoanStatus(1));
                query.setParameter("userId", new CmnUserDetails(userId));
                query.setParameter("bookId", bd);
                List<BookLoanStatus> listBls = query.list();
                if (listBls.isEmpty()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bd.getBookId());
                    jsonObject.put("bookStatus", bd.getIsEnabled());
                    jsonObject.put("bookName", bd.getBookTitle());
                    jsonObject.put("bookAuthorName", bd.getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bd.getTotalPage());
                    jsonObject.put("publishedYear", bd.getPublishedYear().toString());
                    jsonObject.put("publishedBy", bd.getPublisherId().getPublisherName());
                    jsonObject.put("description", bd.getDescriptiion());
                    jsonBook.add(jsonObject);
                }

            }
            object.put("allAvailableBook", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;

    }

    public static JSONObject fetchAllBorrowedBookByUser(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            query.setParameter("userId", new CmnUserDetails(userId));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bls.getBookId().getBookId());
                jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                jsonObject.put("bookName", bls.getBookId().getBookTitle());
                jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                jsonObject.put("borrowedOn", bls.getOnModified() + "");
                jsonObject.put("dueOn", bls.getDueDate() + "");
                jsonBook.add(jsonObject);
            }

            object.put("allBorrowedBook", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }

        return object;
    }

    public static JSONObject fetchAllRequestedBookByUser(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
            query.setParameter("userId", new CmnUserDetails(userId));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bls.getBookId().getBookId());
                jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                jsonObject.put("bookName", bls.getBookId().getBookTitle());
                jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                jsonObject.put("requestedOn", bls.getOnModified() + "");
                jsonBook.add(jsonObject);
            }

            object.put("allRequestedBook", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllReturnedBookByUser(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(2));
            query.setParameter("userId", new CmnUserDetails(userId));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bls.getBookId().getBookId());
                jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                jsonObject.put("bookName", bls.getBookId().getBookTitle());
                jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                jsonObject.put("returnedOn", bls.getOnModified() + "");
                jsonObject.put("createdOn", bls.getOnCreated() + "");
                jsonBook.add(jsonObject);
            }

            object.put("allReturnedBook", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchDashboardStats() {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            List<BookLoanStatus> listBls = query.list();

            object.put("allBorrowedBooks", listBls.size());

            listBls = new ArrayList<BookLoanStatus>();

            hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
            listBls = query.list();

            object.put("allRequestedBook", listBls.size());

            hql = "SELECT c FROM BookDetails c where c.isEnabled=1 and c.isDeleted=0";
            query = session.createQuery(hql);
            List<BookDetails> listBd = query.list();

            object.put("allBooks", listBd.size());

            object.put("responseCode", 1);

        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    private static JSONObject fetchBorrowedBook() {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            List<BookLoanStatus> listBls = query.list();

            JSONArray jsonBook = new JSONArray();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bls.getBookId().getBookId());
                jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                jsonObject.put("bookName", bls.getBookId().getBookTitle());
                jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                jsonObject.put("createdOn", bls.getOnCreated() + "");
                jsonObject.put("userId", bls.getUserId().getUserId());
                jsonObject.put("userName", bls.getUserId().getName());
                jsonBook.add(jsonObject);
            }

            object.put("allTakenbookByUser", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    private static JSONObject fetchRequestedBook() {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
            List<BookLoanStatus> listBls = query.list();

            JSONArray jsonBook = new JSONArray();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookId", bls.getBookId().getBookId());
                jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                jsonObject.put("bookName", bls.getBookId().getBookTitle());
                jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                jsonObject.put("createdOn", bls.getOnCreated() + "");
                jsonObject.put("userId", bls.getUserId().getUserId());
                jsonObject.put("userName", bls.getUserId().getName());
                jsonBook.add(jsonObject);
            }

            object.put("allTakenbookByUser", jsonBook);
            object.put("totalBook", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllBorrowedBook(String searchKey) {

        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(1));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                if (bls.getBookId().getBookTitle().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getName().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getEmailId().toLowerCase().contains(searchKey.toLowerCase())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                    jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                    jsonObject.put("borrowedOn", bls.getOnModified() + "");
                    jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                    jsonObject.put("userId", bls.getUserId().getUserId());
                    jsonObject.put("userName", bls.getUserId().getName());
                    jsonObject.put("emailId", bls.getUserId().getEmailId());
                    jsonObject.put("dueOn", bls.getDueDate() + "");
                    jsonBook.add(jsonObject);
                }
            }

            object.put("allBorrowedBook", jsonBook);
            object.put("totalRecord", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllRequestedBook(String searchKey) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                if (bls.getBookId().getBookTitle().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getName().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getEmailId().toLowerCase().contains(searchKey.toLowerCase())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                    jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                    jsonObject.put("requestedOn", bls.getOnModified() + "");
                    jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                    jsonObject.put("userId", bls.getUserId().getUserId());
                    jsonObject.put("userName", bls.getUserId().getName());
                    jsonBook.add(jsonObject);
                }
            }

            object.put("allRequestedBook", jsonBook);
            object.put("totalRecord", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllAuthor(String searchKey) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM AuthorDetails c where c.isEnabled=1 and c.isDeleted=0 and (c.authorName like :authorName or c.description like :description)";
            Query query = session.createQuery(hql);
            query.setParameter("authorName", "%" + searchKey + "%");
            query.setParameter("description", "%" + searchKey + "%");
            JSONArray jsonBook = new JSONArray();
            List<AuthorDetails> listAd = query.list();
            for (AuthorDetails ad : listAd) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("authorId", ad.getAuthorId());
                jsonObject.put("authorName", ad.getAuthorName());
                jsonObject.put("authorDescription", ad.getDescription());
                jsonObject.put("modifiedOn", ad.getModifiedOn() + "");

                jsonBook.add(jsonObject);
            }

            object.put("allAuthorData", jsonBook);
            object.put("totalRecord", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllPublisher(String searchKey) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM PublisherDetails c where c.isEnabled=1 and c.isDeleted=0 and (c.publisherName like :publisherName or c.description like :description) ";
            Query query = session.createQuery(hql);
            query.setParameter("publisherName", "%" + searchKey + "%");
            query.setParameter("description", "%" + searchKey + "%");
            JSONArray jsonBook = new JSONArray();
            List<PublisherDetails> listPd = query.list();
            for (PublisherDetails pd : listPd) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("publisherId", pd.getPublisherId());
                jsonObject.put("publisherName", pd.getPublisherName());
                jsonObject.put("publisherDescription", pd.getDescription());
                jsonObject.put("modifiedOn", pd.getModifiedOn() + "");

                jsonBook.add(jsonObject);
            }

            object.put("allPublisherData", jsonBook);
            object.put("totalRecord", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject editBookByBookId(BookDetails bd, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            BookDetails bdResult = new BookDetails();
            session.beginTransaction();
            if (bd.getBookId() == null) {
                bdResult.setBookTitle(bd.getBookTitle());
                bdResult.setDescriptiion(bd.getDescriptiion());
                bdResult.setModifiedOn(bd.getModifiedOn());
                bdResult.setCreatedOn(bd.getModifiedOn());
                bdResult.setPublishedYear(bd.getPublishedYear());
                bdResult.setPublisherId(bd.getPublisherId());
                bdResult.setTotalPage(bd.getTotalPage());
                bdResult.setStatusId(new MasterStatus(3));
                bdResult.setCreatedBy(new CmnUserDetails(userId));
                bdResult.setIsDeleted(false);
                bdResult.setIsEnabled(true);
                bdResult.setAuthorId(new AuthorDetails(bd.getAuthorId().getAuthorId()));
                session.saveOrUpdate(bdResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Book has been added");
            } else {
                String hql = "SELECT c FROM BookDetails c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
                Query query = session.createQuery(hql);
                query.setParameter("bookId", bd.getBookId());
                JSONArray jsonBook = new JSONArray();
                bdResult = (BookDetails) query.uniqueResult();
                bdResult.setAuthorId(bd.getAuthorId());
                bdResult.setBookTitle(bd.getBookTitle());
                bdResult.setDescriptiion(bd.getDescriptiion());
                bdResult.setModifiedOn(bd.getModifiedOn());
                bdResult.setPublishedYear(bd.getPublishedYear());
                bdResult.setPublisherId(bd.getPublisherId());
                bdResult.setTotalPage(bd.getTotalPage());
                session.saveOrUpdate(bdResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Book details has been Updated");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchRequestedUserListByBookId(int bookId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.loanStatusId=:loanStatusId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("loanStatusId", new MasterLoanStatus(3));
            JSONArray jsonUser = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", bls.getUserId().getUserId());
                jsonObject.put("userName", bls.getUserId().getName());
                jsonObject.put("emailId", bls.getUserId().getEmailId());
                jsonObject.put("requestOn", bls.getOnModified() + "");

                jsonUser.add(jsonObject);
            }

            object.put("allRequestedUser", jsonUser);
            object.put("totalRecord", jsonUser.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject approveBorrowBook(int userId, int bookId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookDetails c where c.bookId=:bookId and c.statusId=:statusId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", bookId);
            query.setParameter("statusId", new MasterStatus(2));

            BookDetails bd = (BookDetails) query.uniqueResult();
            if (bd != null) {
                bd.setStatusId(new MasterStatus(1));
                session.save(bd);

                hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.userId=:userId and c.loanStatusId=:loanStatusId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
                query = session.createQuery(hql);
                query.setParameter("bookId", new BookDetails(bookId));
                query.setParameter("userId", new CmnUserDetails(userId));
                query.setParameter("loanStatusId", new MasterLoanStatus(3));
                JSONArray jsonUser = new JSONArray();
                List<BookLoanStatus> listBls = query.list();
                for (BookLoanStatus bls : listBls) {
                    bls.setLoanStatusId(new MasterLoanStatus(1));
                    bls.setDueDate(updateDateObject(new Date(), 7));
                    bls.setOnModified(new Date());
                    session.save(bls);
                }

                session.getTransaction().commit();
//                object.put("allRequestedUser", jsonUser);
//                object.put("totalRecord", jsonUser.size());
                object.put("responseCode", 1);
                object.put("msg", "Approved");
            } else {
                object.put("responseCode", 2);
                object.put("msg", "Book is mapped to other user");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchReaderUserListByBookId(int bookId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and (c.loanStatusId=:loanStatusId1 or c.loanStatusId=:loanStatusId2) and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=0";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("loanStatusId1", new MasterLoanStatus(2));
            query.setParameter("loanStatusId2", new MasterLoanStatus(1));
            JSONArray jsonUser = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", bls.getUserId().getUserId());
                jsonObject.put("userName", bls.getUserId().getName());
                jsonObject.put("emailId", bls.getUserId().getEmailId());
                jsonObject.put("requestOn", bls.getOnModified() + "");

                if (bls.getLoanStatusId().getLoanStatusId() == 1) {
                    jsonObject.put("activeUser", true);
                } else {
                    jsonObject.put("activeUser", false);
                }
                jsonUser.add(jsonObject);
            }

            object.put("allReader", jsonUser);
            object.put("totalRecord", jsonUser.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchAllReturnedBook(String searchKey) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:loanStatusId  and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("loanStatusId", new MasterLoanStatus(2));
            JSONArray jsonBook = new JSONArray();
            List<BookLoanStatus> listBls = query.list();
            for (BookLoanStatus bls : listBls) {

                if (bls.getBookId().getBookTitle().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getName().toLowerCase().contains(searchKey.toLowerCase()) || bls.getUserId().getEmailId().toLowerCase().contains(searchKey.toLowerCase())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bookId", bls.getBookId().getBookId());
                    jsonObject.put("bookStatus", bls.getBookId().getIsEnabled());
                    jsonObject.put("bookName", bls.getBookId().getBookTitle());
                    jsonObject.put("bookAuthorName", bls.getBookId().getAuthorId().getAuthorName());
                    jsonObject.put("totalPages", bls.getBookId().getTotalPage());
                    jsonObject.put("publishedYear", bls.getBookId().getPublishedYear() + "");
                    jsonObject.put("returnedOn", bls.getOnModified() + "");
                    jsonObject.put("publishedBy", bls.getBookId().getPublisherId().getPublisherName());
                    jsonObject.put("userId", bls.getUserId().getUserId());
                    jsonObject.put("userName", bls.getUserId().getName());
                    jsonObject.put("emailId", bls.getUserId().getEmailId());
                    jsonBook.add(jsonObject);
                }
            }

            object.put("allReturnedBook", jsonBook);
            object.put("totalRecord", jsonBook.size());
            object.put("responseCode", 1);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject editPublisherByPublishId(PublisherDetails pd, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            PublisherDetails pdResult = new PublisherDetails();
            session.beginTransaction();
            if (pd.getPublisherId() == null) {
//                pdResult.setPublisherId(pd.getPublisherId());
                pdResult.setPublisherName(pd.getPublisherName());
                pdResult.setDescription(pd.getDescription());
                pdResult.setModifiedOn(pd.getModifiedOn());
                pdResult.setCreatedOn(pd.getCreatedOn());
                pdResult.setUserId(new CmnUserDetails(userId));
                pdResult.setIsEnabled(true);
                pdResult.setIsDeleted(false);

                session.saveOrUpdate(pdResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Publisher has been added");
            } else {
                String hql = "SELECT c FROM PublisherDetails c where c.publisherId=:publisherId and c.isEnabled=1 and c.isDeleted=0";
                Query query = session.createQuery(hql);
                query.setParameter("publisherId", pd.getPublisherId());
                JSONArray jsonBook = new JSONArray();
                pdResult = (PublisherDetails) query.uniqueResult();
                pdResult.setPublisherName(pd.getPublisherName());
                pdResult.setDescription(pd.getDescription());
                pdResult.setModifiedOn(pd.getModifiedOn());
                pdResult.setUserId(new CmnUserDetails(userId));
                pdResult.setIsEnabled(true);
                pdResult.setIsDeleted(false);

                session.saveOrUpdate(pdResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Publisher details has been Updated");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject disabledPublisher(int publisherId, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "SELECT c FROM PublisherDetails c where c.publisherId=:publisherId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("publisherId", publisherId);
            PublisherDetails pdResult = (PublisherDetails) query.uniqueResult();
            if (pdResult == null) {
                object.put("responseCode", 3);
                object.put("msg", "Publisher is not valid");
            } else {

                hql = "SELECT c FROM BookDetails c where c.publisherId=:publisherId and c.isEnabled=1 and c.isDeleted=0";
                query = session.createQuery(hql);
                query.setParameter("publisherId", new PublisherDetails(publisherId));
                List<BookDetails> listBd = query.list();
                for (BookDetails bd : listBd) {
                    hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
                    query = session.createQuery(hql);
                    query.setParameter("bookId", bd);
                    List<BookLoanStatus> listBls = query.list();
                    for (BookLoanStatus bls : listBls) {
                        bls.setIsDeleted(true);
                        session.update(bls);
                    }
                    bd.setIsDeleted(true);
                    session.update(bd);
                }
                pdResult.setIsDeleted(true);
                session.update(pdResult);
            }

            session.getTransaction().commit();
            object.put("responseCode", 1);
            object.put("msg", "Publisher is disabled");

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject editAuthorByAuthorId(AuthorDetails ad, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            AuthorDetails adResult = new AuthorDetails();
            session.beginTransaction();
            if (ad.getAuthorId() == null) {
//                pdResult.setPublisherId(pd.getPublisherId());
                adResult.setAuthorName(ad.getAuthorName());
                adResult.setDescription(ad.getDescription());
                adResult.setModifiedOn(ad.getModifiedOn());
                adResult.setCreatedOn(ad.getCreatedOn());
                adResult.setUserId(new CmnUserDetails(userId));
                adResult.setIsEnabled(true);
                adResult.setIsDeleted(false);

                session.saveOrUpdate(adResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Author has been added");
            } else {
                String hql = "SELECT c FROM AuthorDetails c where c.authorId=:authorId and c.isEnabled=1 and c.isDeleted=0";
                Query query = session.createQuery(hql);
                query.setParameter("authorId", ad.getAuthorId());
                JSONArray jsonBook = new JSONArray();
                adResult = (AuthorDetails) query.uniqueResult();
                adResult.setAuthorName(ad.getAuthorName());
                adResult.setDescription(ad.getDescription());
                adResult.setModifiedOn(ad.getModifiedOn());
                adResult.setCreatedOn(ad.getCreatedOn());
                adResult.setUserId(new CmnUserDetails(userId));
                adResult.setIsEnabled(true);
                adResult.setIsDeleted(false);

                session.saveOrUpdate(adResult);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "Author details has been Updated");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject disabledAuthor(int authorId, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "SELECT c FROM AuthorDetails c where c.authorId=:authorId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("authorId", authorId);
            AuthorDetails adResult = (AuthorDetails) query.uniqueResult();
            if (adResult == null) {
                object.put("responseCode", 3);
                object.put("msg", "Author is not valid");
            } else {

                hql = "SELECT c FROM BookDetails c where c.authorId=:authorId and c.isEnabled=1 and c.isDeleted=0";
                query = session.createQuery(hql);
                query.setParameter("authorId", new AuthorDetails(authorId));
                List<BookDetails> listBd = query.list();
                for (BookDetails bd : listBd) {
                    hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
                    query = session.createQuery(hql);
                    query.setParameter("bookId", bd);
                    List<BookLoanStatus> listBls = query.list();
                    for (BookLoanStatus bls : listBls) {
                        bls.setIsDeleted(true);
                        session.update(bls);
                    }
                    bd.setIsDeleted(true);
                    session.update(bd);
                }
                adResult.setIsDeleted(true);
                session.update(adResult);
            }

            session.getTransaction().commit();
            object.put("responseCode", 1);
            object.put("msg", "Author is disabled");

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject fetchUserList(String searchKey) {

        JSONObject jsonreturn = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.roleId!=:roleId and c.isEnabled=1 and c.isDeleted=0 and (c.name like :name or c.emailId like :emailId)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + searchKey + "%");
            query.setParameter("emailId", "%" + searchKey + "%");
            query.setParameter("roleId", new MasterRole(2));
            List<CmnUserDetails> listCud = query.list();
            JSONArray jsonArray = new JSONArray();
            for (CmnUserDetails cud : listCud) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", cud.getUserId());
                jsonObject.put("name", cud.getName());
                jsonObject.put("emailId", cud.getEmailId());

                jsonArray.add(jsonObject);
            }
//          
            jsonreturn.put("allUserList", jsonArray);
            jsonreturn.put("totalRecord", jsonArray.size());
            jsonreturn.put("responseCode", 1);

        } catch (Exception ex) {
            System.out.println(ex);
            jsonreturn.put("responseCode", 0);
        } finally {
            session.close();
        }

        return jsonreturn;
    }

    public static JSONObject editUserByUserId(String userId, String userName, String email) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            PublisherDetails pdResult = new PublisherDetails();
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.emailId!=:emailId";
            Query query = session.createQuery(hql);
            query.setParameter("emailId", email);
            List<CmnUserDetails> listCud = query.list();

            if (!listCud.isEmpty()) {
                object.put("responseCode", 3);
                object.put("msg", "EmailId Already exist");
            } else {
                CmnUserDetails cud = new CmnUserDetails();
                cud.setName(userName);
                cud.setEmailId(email);
                cud.setPassword(CommonUtil.plainToHashPassword("LB@2021"));
                cud.setName(email);
                cud.setName(email);
                cud.setIsEnabled(true);
                cud.setIsDeleted(false);
                cud.setRoleId(new MasterRole(1));
                session.save(cud);
                session.getTransaction().commit();
                object.put("responseCode", 1);
                object.put("msg", "User added");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject disabledUser(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("userId", userId);
            CmnUserDetails cud = (CmnUserDetails) query.uniqueResult();
            if (cud == null) {
                object.put("responseCode", 3);
                object.put("msg", "User is not valid");
            } else {

                cud.setIsDeleted(true);
                cud.setModifiedOn(new Date());
                session.save(cud);
            }

            session.getTransaction().commit();
            object.put("responseCode", 1);
            object.put("msg", "User is disabled");

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject resetPassword(int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "SELECT c FROM CmnUserDetails c where c.userId=:userId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("userId", userId);
            CmnUserDetails cud = (CmnUserDetails) query.uniqueResult();
            if (cud == null) {
                object.put("responseCode", 3);
                object.put("msg", "User is not valid");
            } else {
                cud.setPassword(CommonUtil.plainToHashPassword("LB@2021"));
                cud.setModifiedOn(new Date());
                session.save(cud);
            }

            session.getTransaction().commit();
            object.put("responseCode", 1);
            object.put("msg", "Passwrod is reset");

        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

    public static JSONObject renewBook(int userId, int bookId) {

        JSONObject object = new JSONObject();
        int returnData = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            String hql = "SELECT c FROM BookLoanStatus c where c.loanStatusId=:statusId and c.bookId=:bookId and c.userId=:userId and c.isEnabled=1 and c.isDeleted=0 and c.isCanceled=:isCanceled ";

            Query query = session.createQuery(hql);
            query.setParameter("statusId", new MasterLoanStatus(1));
            query.setParameter("bookId", new BookDetails(bookId));
            query.setParameter("userId", new CmnUserDetails(userId));
            query.setParameter("isCanceled", false);
            List<BookLoanStatus> listBls = query.list();

            if (listBls.isEmpty()) {
                object.put("responseCode", 3);
                object.put("msg", "Book is returned to Library already");
            } else {
                tx = session.beginTransaction();

                BookLoanStatus bls = listBls.get(listBls.size() - 1);
                if (bls.getDueDate().after(new Date())) {
                    bls.setDueDate(updateDateObject(bls.getDueDate(), 7));
                } else {
                    bls.setDueDate(updateDateObject(new Date(), 7));
                }

                session.save(bls);
                tx.commit();
                object.put("responseCode", 1);
                object.put("msg", "Book is renewd by you successfully");

            }

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.getMessage());
            object.put("responseCode", 0);
            object.put("msg", "Some Error Occured. Please try after some time.");

        } finally {
            session.close();
        }

        return object;

    }

    public static JSONObject renewOrUpdateBook(int userId, int bookId, int operation) {

        if (operation == 1) {
            return returnBook(userId, bookId);
        } else if (operation == 2) {
            return renewBook(userId, bookId);
        } else {
            JSONObject object = new JSONObject();
            object.put("responseCode", 2);
            object.put("msg", "Some Error Occured");
            return object;
        }

    }

    public static JSONObject disabledBook(int bookId, int userId) {
        JSONObject object = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            String hql = "SELECT c FROM BookDetails c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", bookId);
            List<BookDetails> listBd = query.list();
            for (BookDetails bd : listBd) {
                hql = "SELECT c FROM BookLoanStatus c where c.bookId=:bookId and c.isEnabled=1 and c.isDeleted=0";
                query = session.createQuery(hql);
                query.setParameter("bookId", bd);
                List<BookLoanStatus> listBls = query.list();
                for (BookLoanStatus bls : listBls) {
                    bls.setIsDeleted(true);
                    session.update(bls);
                }
                bd.setIsDeleted(true);
                session.update(bd);
            }
            session.getTransaction().commit();
            object.put("responseCode", 1);
            object.put("msg", "Boook is disabled");
        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
            object.put("responseCode", 0);
        } finally {
            session.close();
        }
        return object;
    }

}
