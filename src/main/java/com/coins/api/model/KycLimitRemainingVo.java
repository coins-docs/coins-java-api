package com.coins.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KycLimitRemainingVo {
    private String tokenId;
    private String fiatSymbol;
    private RemainingDetail daily;
    private RemainingDetail monthly;
    private RemainingDetail annually;

    public static KycLimitRemainingVoBuilder builder() {
        return new KycLimitRemainingVoBuilder();
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getFiatSymbol() {
        return this.fiatSymbol;
    }

    public RemainingDetail getDaily() {
        return this.daily;
    }

    public RemainingDetail getMonthly() {
        return this.monthly;
    }

    public RemainingDetail getAnnually() {
        return this.annually;
    }

    public void setTokenId(final String tokenId) {
        this.tokenId = tokenId;
    }

    public void setFiatSymbol(final String fiatSymbol) {
        this.fiatSymbol = fiatSymbol;
    }

    public void setDaily(final RemainingDetail daily) {
        this.daily = daily;
    }

    public void setMonthly(final RemainingDetail monthly) {
        this.monthly = monthly;
    }

    public void setAnnually(final RemainingDetail annually) {
        this.annually = annually;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof KycLimitRemainingVo)) {
            return false;
        } else {
            KycLimitRemainingVo other = (KycLimitRemainingVo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$tokenId = this.getTokenId();
                    Object other$tokenId = other.getTokenId();
                    if (this$tokenId == null) {
                        if (other$tokenId == null) {
                            break label71;
                        }
                    } else if (this$tokenId.equals(other$tokenId)) {
                        break label71;
                    }

                    return false;
                }

                Object this$fiatSymbol = this.getFiatSymbol();
                Object other$fiatSymbol = other.getFiatSymbol();
                if (this$fiatSymbol == null) {
                    if (other$fiatSymbol != null) {
                        return false;
                    }
                } else if (!this$fiatSymbol.equals(other$fiatSymbol)) {
                    return false;
                }

                label57: {
                    Object this$daily = this.getDaily();
                    Object other$daily = other.getDaily();
                    if (this$daily == null) {
                        if (other$daily == null) {
                            break label57;
                        }
                    } else if (this$daily.equals(other$daily)) {
                        break label57;
                    }

                    return false;
                }

                Object this$monthly = this.getMonthly();
                Object other$monthly = other.getMonthly();
                if (this$monthly == null) {
                    if (other$monthly != null) {
                        return false;
                    }
                } else if (!this$monthly.equals(other$monthly)) {
                    return false;
                }

                Object this$annually = this.getAnnually();
                Object other$annually = other.getAnnually();
                if (this$annually == null) {
                    if (other$annually == null) {
                        return true;
                    }
                } else if (this$annually.equals(other$annually)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof KycLimitRemainingVo;
    }

    public int hashCode() {
        int result = 1;
        Object $tokenId = this.getTokenId();
        result = result * 59 + ($tokenId == null ? 43 : $tokenId.hashCode());
        Object $fiatSymbol = this.getFiatSymbol();
        result = result * 59 + ($fiatSymbol == null ? 43 : $fiatSymbol.hashCode());
        Object $daily = this.getDaily();
        result = result * 59 + ($daily == null ? 43 : $daily.hashCode());
        Object $monthly = this.getMonthly();
        result = result * 59 + ($monthly == null ? 43 : $monthly.hashCode());
        Object $annually = this.getAnnually();
        result = result * 59 + ($annually == null ? 43 : $annually.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getTokenId();
        return "KycLimitRemainingVo(tokenId=" + var10000 + ", fiatSymbol=" + this.getFiatSymbol() + ", daily=" + this.getDaily() + ", monthly=" + this.getMonthly() + ", annually=" + this.getAnnually() + ")";
    }

    public KycLimitRemainingVo() {
    }

    public KycLimitRemainingVo(final String tokenId, final String fiatSymbol, final RemainingDetail daily, final RemainingDetail monthly, final RemainingDetail annually) {
        this.tokenId = tokenId;
        this.fiatSymbol = fiatSymbol;
        this.daily = daily;
        this.monthly = monthly;
        this.annually = annually;
    }

    public static class KycLimitRemainingVoBuilder {
        private String tokenId;
        private String fiatSymbol;
        private RemainingDetail daily;
        private RemainingDetail monthly;
        private RemainingDetail annually;

        KycLimitRemainingVoBuilder() {
        }

        public KycLimitRemainingVoBuilder tokenId(final String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public KycLimitRemainingVoBuilder fiatSymbol(final String fiatSymbol) {
            this.fiatSymbol = fiatSymbol;
            return this;
        }

        public KycLimitRemainingVoBuilder daily(final RemainingDetail daily) {
            this.daily = daily;
            return this;
        }

        public KycLimitRemainingVoBuilder monthly(final RemainingDetail monthly) {
            this.monthly = monthly;
            return this;
        }

        public KycLimitRemainingVoBuilder annually(final RemainingDetail annually) {
            this.annually = annually;
            return this;
        }

        public KycLimitRemainingVo build() {
            return new KycLimitRemainingVo(this.tokenId, this.fiatSymbol, this.daily, this.monthly, this.annually);
        }

        public String toString() {
            return "KycLimitRemainingVo.KycLimitRemainingVoBuilder(tokenId=" + this.tokenId + ", fiatSymbol=" + this.fiatSymbol + ", daily=" + this.daily + ", monthly=" + this.monthly + ", annually=" + this.annually + ")";
        }
    }

    public static class RemainingDetail {
        private BigDecimal cashInLimit;
        private BigDecimal cashInRemaining;
        private BigDecimal cashOutLimit;
        private BigDecimal cashOutRemaining;
        private BigDecimal totalWithdrawLimit;
        private BigDecimal totalWithdrawRemaining;

        public static RemainingDetailBuilder builder() {
            return new RemainingDetailBuilder();
        }

        public BigDecimal getCashInLimit() {
            return this.cashInLimit;
        }

        public BigDecimal getCashInRemaining() {
            return this.cashInRemaining;
        }

        public BigDecimal getCashOutLimit() {
            return this.cashOutLimit;
        }

        public BigDecimal getCashOutRemaining() {
            return this.cashOutRemaining;
        }

        public BigDecimal getTotalWithdrawLimit() {
            return this.totalWithdrawLimit;
        }

        public BigDecimal getTotalWithdrawRemaining() {
            return this.totalWithdrawRemaining;
        }

        public void setCashInLimit(final BigDecimal cashInLimit) {
            this.cashInLimit = cashInLimit;
        }

        public void setCashInRemaining(final BigDecimal cashInRemaining) {
            this.cashInRemaining = cashInRemaining;
        }

        public void setCashOutLimit(final BigDecimal cashOutLimit) {
            this.cashOutLimit = cashOutLimit;
        }

        public void setCashOutRemaining(final BigDecimal cashOutRemaining) {
            this.cashOutRemaining = cashOutRemaining;
        }

        public void setTotalWithdrawLimit(final BigDecimal totalWithdrawLimit) {
            this.totalWithdrawLimit = totalWithdrawLimit;
        }

        public void setTotalWithdrawRemaining(final BigDecimal totalWithdrawRemaining) {
            this.totalWithdrawRemaining = totalWithdrawRemaining;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof RemainingDetail)) {
                return false;
            } else {
                RemainingDetail other = (RemainingDetail)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$cashInLimit = this.getCashInLimit();
                    Object other$cashInLimit = other.getCashInLimit();
                    if (this$cashInLimit == null) {
                        if (other$cashInLimit != null) {
                            return false;
                        }
                    } else if (!this$cashInLimit.equals(other$cashInLimit)) {
                        return false;
                    }

                    Object this$cashInRemaining = this.getCashInRemaining();
                    Object other$cashInRemaining = other.getCashInRemaining();
                    if (this$cashInRemaining == null) {
                        if (other$cashInRemaining != null) {
                            return false;
                        }
                    } else if (!this$cashInRemaining.equals(other$cashInRemaining)) {
                        return false;
                    }

                    Object this$cashOutLimit = this.getCashOutLimit();
                    Object other$cashOutLimit = other.getCashOutLimit();
                    if (this$cashOutLimit == null) {
                        if (other$cashOutLimit != null) {
                            return false;
                        }
                    } else if (!this$cashOutLimit.equals(other$cashOutLimit)) {
                        return false;
                    }

                    label62: {
                        Object this$cashOutRemaining = this.getCashOutRemaining();
                        Object other$cashOutRemaining = other.getCashOutRemaining();
                        if (this$cashOutRemaining == null) {
                            if (other$cashOutRemaining == null) {
                                break label62;
                            }
                        } else if (this$cashOutRemaining.equals(other$cashOutRemaining)) {
                            break label62;
                        }

                        return false;
                    }

                    label55: {
                        Object this$totalWithdrawLimit = this.getTotalWithdrawLimit();
                        Object other$totalWithdrawLimit = other.getTotalWithdrawLimit();
                        if (this$totalWithdrawLimit == null) {
                            if (other$totalWithdrawLimit == null) {
                                break label55;
                            }
                        } else if (this$totalWithdrawLimit.equals(other$totalWithdrawLimit)) {
                            break label55;
                        }

                        return false;
                    }

                    Object this$totalWithdrawRemaining = this.getTotalWithdrawRemaining();
                    Object other$totalWithdrawRemaining = other.getTotalWithdrawRemaining();
                    if (this$totalWithdrawRemaining == null) {
                        if (other$totalWithdrawRemaining != null) {
                            return false;
                        }
                    } else if (!this$totalWithdrawRemaining.equals(other$totalWithdrawRemaining)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof RemainingDetail;
        }

        public int hashCode() {
            int result = 1;
            Object $cashInLimit = this.getCashInLimit();
            result = result * 59 + ($cashInLimit == null ? 43 : $cashInLimit.hashCode());
            Object $cashInRemaining = this.getCashInRemaining();
            result = result * 59 + ($cashInRemaining == null ? 43 : $cashInRemaining.hashCode());
            Object $cashOutLimit = this.getCashOutLimit();
            result = result * 59 + ($cashOutLimit == null ? 43 : $cashOutLimit.hashCode());
            Object $cashOutRemaining = this.getCashOutRemaining();
            result = result * 59 + ($cashOutRemaining == null ? 43 : $cashOutRemaining.hashCode());
            Object $totalWithdrawLimit = this.getTotalWithdrawLimit();
            result = result * 59 + ($totalWithdrawLimit == null ? 43 : $totalWithdrawLimit.hashCode());
            Object $totalWithdrawRemaining = this.getTotalWithdrawRemaining();
            result = result * 59 + ($totalWithdrawRemaining == null ? 43 : $totalWithdrawRemaining.hashCode());
            return result;
        }

        public String toString() {
            BigDecimal var10000 = this.getCashInLimit();
            return "KycLimitRemainingVo.RemainingDetail(cashInLimit=" + var10000 + ", cashInRemaining=" + this.getCashInRemaining() + ", cashOutLimit=" + this.getCashOutLimit() + ", cashOutRemaining=" + this.getCashOutRemaining() + ", totalWithdrawLimit=" + this.getTotalWithdrawLimit() + ", totalWithdrawRemaining=" + this.getTotalWithdrawRemaining() + ")";
        }

        public RemainingDetail() {
        }

        public RemainingDetail(final BigDecimal cashInLimit, final BigDecimal cashInRemaining, final BigDecimal cashOutLimit, final BigDecimal cashOutRemaining, final BigDecimal totalWithdrawLimit, final BigDecimal totalWithdrawRemaining) {
            this.cashInLimit = cashInLimit;
            this.cashInRemaining = cashInRemaining;
            this.cashOutLimit = cashOutLimit;
            this.cashOutRemaining = cashOutRemaining;
            this.totalWithdrawLimit = totalWithdrawLimit;
            this.totalWithdrawRemaining = totalWithdrawRemaining;
        }

        public static class RemainingDetailBuilder {
            private BigDecimal cashInLimit;
            private BigDecimal cashInRemaining;
            private BigDecimal cashOutLimit;
            private BigDecimal cashOutRemaining;
            private BigDecimal totalWithdrawLimit;
            private BigDecimal totalWithdrawRemaining;

            RemainingDetailBuilder() {
            }

            public RemainingDetailBuilder cashInLimit(final BigDecimal cashInLimit) {
                this.cashInLimit = cashInLimit;
                return this;
            }

            public RemainingDetailBuilder cashInRemaining(final BigDecimal cashInRemaining) {
                this.cashInRemaining = cashInRemaining;
                return this;
            }

            public RemainingDetailBuilder cashOutLimit(final BigDecimal cashOutLimit) {
                this.cashOutLimit = cashOutLimit;
                return this;
            }

            public RemainingDetailBuilder cashOutRemaining(final BigDecimal cashOutRemaining) {
                this.cashOutRemaining = cashOutRemaining;
                return this;
            }

            public RemainingDetailBuilder totalWithdrawLimit(final BigDecimal totalWithdrawLimit) {
                this.totalWithdrawLimit = totalWithdrawLimit;
                return this;
            }

            public RemainingDetailBuilder totalWithdrawRemaining(final BigDecimal totalWithdrawRemaining) {
                this.totalWithdrawRemaining = totalWithdrawRemaining;
                return this;
            }

            public RemainingDetail build() {
                return new RemainingDetail(this.cashInLimit, this.cashInRemaining, this.cashOutLimit, this.cashOutRemaining, this.totalWithdrawLimit, this.totalWithdrawRemaining);
            }

            public String toString() {
                return "KycLimitRemainingVo.RemainingDetail.RemainingDetailBuilder(cashInLimit=" + this.cashInLimit + ", cashInRemaining=" + this.cashInRemaining + ", cashOutLimit=" + this.cashOutLimit + ", cashOutRemaining=" + this.cashOutRemaining + ", totalWithdrawLimit=" + this.totalWithdrawLimit + ", totalWithdrawRemaining=" + this.totalWithdrawRemaining + ")";
            }
        }
    }
}

